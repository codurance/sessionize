package com.codurance.sessionize.sessionizeservice.matching.service;

import com.codurance.sessionize.sessionizeservice.matching.client.MatchResponse;
import com.codurance.sessionize.sessionizeservice.matching.client.MatchingClient;
import com.codurance.sessionize.sessionizeservice.pairing.Pairing;
import com.codurance.sessionize.sessionizeservice.pairing.Status;
import com.codurance.sessionize.sessionizeservice.pairing.repository.PairingsRepository;
import com.codurance.sessionize.sessionizeservice.preferences.AvailableLanguages;
import com.codurance.sessionize.sessionizeservice.preferences.Language;
import com.codurance.sessionize.sessionizeservice.preferences.repository.CustomPreferencesRepository;
import com.codurance.sessionize.sessionizeservice.preferences.UserLanguagePreferences;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.HttpServerErrorException;

import java.util.ArrayList;
import java.util.List;

public class MatchingServiceImpl implements MatchingService {

    private final MatchingClient matchingClient;
    private final CustomPreferencesRepository preferencesRepository;
    private final PairingsRepository pairingsRepository;

    @Autowired
    public MatchingServiceImpl(MatchingClient matchingClient, CustomPreferencesRepository preferencesRepository, PairingsRepository pairingsRepository) {
        this.matchingClient = matchingClient;
        this.preferencesRepository = preferencesRepository;
        this.pairingsRepository = pairingsRepository;
    }

    public List<MatchResponse> getMatchesForUserPreferences() throws HttpServerErrorException {
        List<UserLanguagePreferences> userLanguagePreferences = preferencesRepository.getUserLanguagePreferences();
        return matchingClient.match(userLanguagePreferences);
    }

    public List<Pairing> mapAsPairing(List<MatchResponse> matches) {

        List<Pairing> pairings = new ArrayList<>();

        matches.forEach(
          matchResponse -> {

              Pairing pairing = new Pairing();

              if (matchResponse.getUsers().size() == 1) { //TODO: split to util method
                pairing.setUsers(matchResponse.getUsers());
                pairing.setStatus(Status.UNSUCCESSFUL);
                pairing.setLanguage(new Language("N/A", "N/A"));
                pairings.add(pairing);
              } else {
                pairing.setLanguage(new Language(matchResponse.getLanguage(), getDisplayNameForLanguage(matchResponse.getLanguage())));
                pairing.setStatus(Status.PENDING);
                pairings.add(pairing); //TODO: create helper methods for success/not success mapping
              }
          }
        );
        return pairings;
    }

  private String getDisplayNameForLanguage(String language) {
    for (Language l: AvailableLanguages.get()) {
      if (l.getValue().equals(language)) {
        return l.getDisplayName();
      }
    }
    return "N/A";
  }

  public List<Pairing> generate() throws HttpServerErrorException {
    List<MatchResponse> matches = getMatchesForUserPreferences();
    List<Pairing> pairings = mapAsPairing(matches);
    pairings.forEach(pairingsRepository::save);
    return pairings;
  }
}
