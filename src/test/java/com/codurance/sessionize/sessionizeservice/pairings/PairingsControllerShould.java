package com.codurance.sessionize.sessionizeservice.pairings;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PairingsControllerShould {

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
    public void return_pairing_on_get_request() {
        PairingRepository repository = mock(PairingRepository.class);
        PairingsController controller = new PairingsController(repository);
        List<Pairing> pairings = new ArrayList<>();
        pairings.add(new Pairing());
        pairings.add(new Pairing());
        when(repository.getPairings("sophie.biber@codurance.com")).thenReturn(
                pairings
        );
        ResponseEntity<List<Pairing>> response = controller.getPairings("sophie.biber@codurance.com");
        List<Pairing> pairing = response.getBody();
        assertThat(new Pairing()).isEqualToComparingFieldByField(pairing);
    }




}
