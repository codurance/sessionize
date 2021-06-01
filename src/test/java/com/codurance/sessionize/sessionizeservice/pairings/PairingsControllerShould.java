package com.codurance.sessionize.sessionizeservice.pairings;

import com.codurance.sessionize.sessionizeservice.pairings.controller.PairingsController;
import com.codurance.sessionize.sessionizeservice.pairings.repository.PairingsRepository;
import com.codurance.sessionize.sessionizeservice.pairings.service.PairingsService;
import com.codurance.sessionize.sessionizeservice.pairings.service.PairingsServiceImpl;
import com.codurance.sessionize.sessionizeservice.preferences.LanguagesPreferences;
import com.codurance.sessionize.sessionizeservice.user.User;
import com.codurance.sessionize.sessionizeservice.user.repository.UserRepository;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static java.util.Arrays.asList;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PairingsControllerShould {

    WireMockServer wireMockServer = new WireMockServer(options().port(8080));
    private PairingsRepository mockPairingsRepository;
    private UserRepository mockUserRepository;
    private PairingsService pairingsService;
    private PairingsController controller;

    @BeforeEach
    public void setup() {
        mockPairingsRepository = mock(PairingsRepository.class);
        mockUserRepository = mock(UserRepository.class);
        pairingsService = new PairingsServiceImpl(mockPairingsRepository, mockUserRepository);
        controller = new PairingsController(pairingsService);
    }

    @Test
    void return_pairing_on_get_request() {
        Pairing firstPairing = new Pairing();
        firstPairing.setUsers(asList("sophieId", "partnerId"));

        Pairing secondPairing = new Pairing();
        secondPairing.setUsers(asList("sophieId", "partnerId"));

        List<Pairing> pairings = asList(firstPairing, secondPairing);
        User sophie = new User("sophieId",
                "sophieSlackId",
                "sophie.biber@codurance.com",
                "url.co",
                "sophie",
                "biber",
                true,
                new LanguagesPreferences());

        when(mockUserRepository.findUserByEmail("sophie.biber@codurance.com")).thenReturn(sophie);
        when(mockPairingsRepository.findPairingsByUserId("sophieId")).thenReturn(pairings);

        ResponseEntity<List<Pairing>> response = controller.getPairings("sophie.biber@codurance.com");
        List<Pairing> pairing = response.getBody();

        Pairing first = pairing.stream().findFirst().orElseThrow();
        assertThat(firstPairing).usingRecursiveComparison().isEqualTo(first);
    }

}
