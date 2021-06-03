package com.codurance.sessionize.sessionizeservice.matching.service;

import com.codurance.sessionize.sessionizeservice.matching.MatchesDTO;
import com.codurance.sessionize.sessionizeservice.matching.client.MatchingClient;
import com.codurance.sessionize.sessionizeservice.pairing.Pairing;
import com.codurance.sessionize.sessionizeservice.pairing.Status;
import com.codurance.sessionize.sessionizeservice.pairing.repository.PairingsRepository;
import com.codurance.sessionize.sessionizeservice.preferences.repository.CustomPreferencesRepository;
import com.codurance.sessionize.sessionizeservice.preferences.repository.UserLanguagePreferences;
import org.springframework.beans.factory.annotation.Autowired;

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

    public MatchesDTO getMatches() {
        List<UserLanguagePreferences> userLanguagePreferences = preferencesRepository.getUserLanguagePreferences();
        return matchingClient.match(userLanguagePreferences);
    }

    public List<Pairing> mapToPairing(MatchesDTO matchesDTO) {

        List<Pairing> pairings = new ArrayList<>();

        matchesDTO.getMatches().forEach(
          pairingDTO -> {
              Pairing pairing = new Pairing();
              pairing.setLanguage(pairingDTO.getLanguage());
              pairing.setUsers(pairingDTO.getUserEmails());
              pairing.setStatus(Status.PENDING);
              pairings.add(pairing);
          }
        );
        return pairings;
    }

    public void generate() {
        MatchesDTO matches = getMatches();
        List<Pairing> pairings = mapToPairing(matches);
        pairings.forEach(pairingsRepository::save);
    }
}
