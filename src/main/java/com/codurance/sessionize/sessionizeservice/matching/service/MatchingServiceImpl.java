package com.codurance.sessionize.sessionizeservice.matching.service;

import com.codurance.sessionize.sessionizeservice.matching.MatchesDTO;
import com.codurance.sessionize.sessionizeservice.matching.client.MatchingClient;
import com.codurance.sessionize.sessionizeservice.pairing.repository.PairingsRepository;
import com.codurance.sessionize.sessionizeservice.preferences.repository.CustomPreferencesRepository;
import com.codurance.sessionize.sessionizeservice.preferences.repository.UserLanguagePreferences;
import org.springframework.beans.factory.annotation.Autowired;

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

    public MatchesDTO generate() {
        List<UserLanguagePreferences> userLanguagePreferences = preferencesRepository.getUserLanguagePreferences();
        return matchingClient.match(userLanguagePreferences);
    }

    public void mapToPairing(MatchesDTO matchesDTO) {
        //TODO: before saving,
        // add "STATUS - PENDING"
        // and sort display name stuff
        // time/date is not relevant for now
        matchesDTO.getMatches().forEach(pairingsRepository::save);
    }


}
