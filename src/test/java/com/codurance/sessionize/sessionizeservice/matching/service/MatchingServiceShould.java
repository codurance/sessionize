package com.codurance.sessionize.sessionizeservice.matching.service;

import com.codurance.sessionize.sessionizeservice.matching.client.MatchResponse;
import com.codurance.sessionize.sessionizeservice.matching.client.MatchingClient;
import com.codurance.sessionize.sessionizeservice.matching.service.MatchingService;
import com.codurance.sessionize.sessionizeservice.matching.service.MatchingServiceImpl;
import com.codurance.sessionize.sessionizeservice.pairing.Pairing;
import com.codurance.sessionize.sessionizeservice.pairing.repository.PairingsRepository;
import com.codurance.sessionize.sessionizeservice.preferences.Language;
import com.codurance.sessionize.sessionizeservice.preferences.LanguagesPreferences;
import com.codurance.sessionize.sessionizeservice.preferences.repository.CustomPreferencesRepository;
import com.codurance.sessionize.sessionizeservice.preferences.UserLanguagePreferences;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class MatchingServiceShould {
    private MatchingService matchingService;
    private CustomPreferencesRepository mockedPreferencesRepository;
    private MatchingClient mockedMatchingClient;
    private PairingsRepository mockedPairingsRepository;

    @BeforeEach
    void setUp() {
        mockedMatchingClient = mock(MatchingClient.class);
        mockedPreferencesRepository = mock(CustomPreferencesRepository.class);
        mockedPairingsRepository = mock(PairingsRepository.class);
        matchingService = new MatchingServiceImpl(mockedMatchingClient, mockedPreferencesRepository, mockedPairingsRepository);
    }

    @Test
    void generate_matches() {

        List<UserLanguagePreferences> preferences = new ArrayList<>();
        when(mockedPreferencesRepository.getUserLanguagePreferences()).thenReturn(preferences);

        matchingService.generate();

        verify(mockedPreferencesRepository, times(1)).getUserLanguagePreferences();
        verify(mockedMatchingClient, times(1)).match(preferences);
    }

    @Test
    void maps_pairing_from_response() {

        List<MatchResponse> matches = Collections.singletonList(new MatchResponse(Collections.singletonList("andras"), "JavaScript"));

        List<Pairing> pairings = matchingService.mapAsPairing(matches);

        assertTrue(pairings.get(0).getUsers().contains("andras"));
        assertEquals("JavaScript", pairings.get(0).getLanguage().getValue());
    }


}
