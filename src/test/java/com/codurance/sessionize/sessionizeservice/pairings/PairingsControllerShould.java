package com.codurance.sessionize.sessionizeservice.pairings;

import com.codurance.sessionize.sessionizeservice.authentication.AuthenticationController;
import com.codurance.sessionize.sessionizeservice.authentication.TokenVerification;
import com.codurance.sessionize.sessionizeservice.authentication.User;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.json.webtoken.JsonWebSignature;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

import static com.codurance.sessionize.sessionizeservice.utils.Constants.*;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PairingsControllerShould {

    private static final String BASE_URL = "http://localhost:8080";

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
    public void return_pairing_on_get_request() throws IOException {
        PairingRepository repository = mock(PairingRepository.class);
        PairingsController controller = new PairingsController(repository);
        when(repository.getPairings("blah")).thenReturn(
                new Pairing());
        ResponseEntity<Pairing> response = controller.getPairings("blah");
        Pairing pairing = response.getBody();
        assertThat(new Pairing()).isEqualToComparingFieldByField(pairing);
    }




}
