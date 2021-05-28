package com.codurance.sessionize.sessionizeservice.pairings;

import com.codurance.sessionize.sessionizeservice.preferences.repository.CustomPreferencesRepository;
import com.codurance.sessionize.sessionizeservice.preferences.repository.UserLanguagePreferences;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PairingsServiceImpl implements PairingsService {
    private final MatchingClient matchingClient;
    private final CustomPreferencesRepository preferencesRepository;

    @Autowired
    public PairingsServiceImpl(MatchingClient matchingClient, CustomPreferencesRepository preferencesRepository) {
        this.matchingClient = matchingClient;
        this.preferencesRepository = preferencesRepository;
    }

    public List<PairingsResponse> generate() {
        List<UserLanguagePreferences> userLanguagePreferences = preferencesRepository.getUserLanguagePreferences();
        return matchingClient.getPairings(userLanguagePreferences);
    }
}
