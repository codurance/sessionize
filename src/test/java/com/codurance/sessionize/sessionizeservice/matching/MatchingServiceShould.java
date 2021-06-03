package com.codurance.sessionize.sessionizeservice.matching;

import com.codurance.sessionize.sessionizeservice.matching.client.MatchingClient;
import com.codurance.sessionize.sessionizeservice.matching.service.MatchingService;
import com.codurance.sessionize.sessionizeservice.matching.service.MatchingServiceImpl;
import com.codurance.sessionize.sessionizeservice.pairing.repository.PairingsRepository;
import com.codurance.sessionize.sessionizeservice.preferences.Language;
import com.codurance.sessionize.sessionizeservice.preferences.LanguagesPreferences;
import com.codurance.sessionize.sessionizeservice.preferences.repository.CustomPreferencesRepository;
import com.codurance.sessionize.sessionizeservice.preferences.repository.UserLanguagePreferences;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static java.util.Arrays.asList;
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
    void call_the_matching_function() throws IOException {
        LanguagesPreferences languagesPreferences = new LanguagesPreferences(
                new Language("RUST", "Rust"),
                new Language("FSHARP", "F#"),
                new Language("VISUALBASIC", "VB"));

        List<UserLanguagePreferences> userLanguagePreferences = asList(
                new UserLanguagePreferences("user1@example.com", languagesPreferences),
                new UserLanguagePreferences("user2@example.com", languagesPreferences)
        );
        when(mockedPreferencesRepository.getUserLanguagePreferences()).thenReturn(userLanguagePreferences);

        MatchesDTO pairings = matchingService.getMatches();

        verify(mockedPreferencesRepository, times(1)).getUserLanguagePreferences();
        verify(mockedMatchingClient, times(1)).match(userLanguagePreferences);
    }
}
