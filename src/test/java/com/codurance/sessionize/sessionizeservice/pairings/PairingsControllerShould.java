package com.codurance.sessionize.sessionizeservice.pairings;

import com.codurance.sessionize.sessionizeservice.pairings.controller.PairingsController;
import com.codurance.sessionize.sessionizeservice.pairings.repository.PairingRepository;
import com.codurance.sessionize.sessionizeservice.preferences.repository.CustomPreferencesRepository;
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

    @BeforeEach
    public void setup() {
        wireMockServer.start();
    }

    @AfterEach
    public void teardown() {
        wireMockServer.stop();
    }

    @Test
    void return_pairing_on_get_request() {
        PairingRepository repository = mock(PairingRepository.class);
        PairingsController controller = new PairingsController(repository, mock(CustomPreferencesRepository.class));
        List<Pairing> pairings = asList(new Pairing(), new Pairing());
        when(repository.getPairings("sophie.biber@codurance.com")).thenReturn(pairings);

        ResponseEntity<List<Pairing>> response = controller.getPairings("sophie.biber@codurance.com");
        List<Pairing> pairing = response.getBody();

        Pairing first = pairing.stream().findFirst().orElseThrow();
        assertThat(new Pairing()).usingRecursiveComparison().isEqualTo(first);
    }
}
