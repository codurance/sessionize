package com.codurance.sessionize.sessionizeservice.match.service;

import com.codurance.sessionize.sessionizeservice.pairings.MatchingClient;
import com.codurance.sessionize.sessionizeservice.pairings.PairingsResponse;
import com.codurance.sessionize.sessionizeservice.preferences.repository.CustomPreferencesRepository;
import com.codurance.sessionize.sessionizeservice.preferences.repository.UserLanguagePreferences;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;

public class MatchingServiceImpl implements MatchingService {
    private final MatchingClient matchingClient;
    private final CustomPreferencesRepository preferencesRepository;

    @Autowired
    public MatchingServiceImpl(MatchingClient matchingClient, CustomPreferencesRepository preferencesRepository) {
        this.matchingClient = matchingClient;
        this.preferencesRepository = preferencesRepository;
    }

    public List<PairingsResponse> generate() throws IOException {
        List<UserLanguagePreferences> userLanguagePreferences = preferencesRepository.getUserLanguagePreferences();
        return matchingClient.getPairings(userLanguagePreferences);
    }
}
