package com.codurance.sessionize.sessionizeservice.match;

import com.codurance.sessionize.sessionizeservice.match.service.MatchingService;
import com.codurance.sessionize.sessionizeservice.match.service.MatchingServiceImpl;
import com.codurance.sessionize.sessionizeservice.pairings.MatchingClient;
import com.codurance.sessionize.sessionizeservice.pairings.PairingsResponse;
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

    @BeforeEach
    void setUp() {
        mockedMatchingClient = mock(MatchingClient.class);
        mockedPreferencesRepository = mock(CustomPreferencesRepository.class);
        matchingService = new MatchingServiceImpl(mockedMatchingClient, mockedPreferencesRepository);
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

        List<PairingsResponse> pairings = matchingService.generate();

        verify(mockedPreferencesRepository, times(1)).getUserLanguagePreferences();
        verify(mockedMatchingClient, times(1)).getPairings(userLanguagePreferences);
    }
}
