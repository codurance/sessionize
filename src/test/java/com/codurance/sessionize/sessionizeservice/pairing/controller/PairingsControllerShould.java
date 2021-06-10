package com.codurance.sessionize.sessionizeservice.pairing.controller;

import com.codurance.sessionize.sessionizeservice.infrastructure.security.TokenVerification;
import com.codurance.sessionize.sessionizeservice.pairing.Status;
import com.codurance.sessionize.sessionizeservice.pairing.client.SlackClient;
import com.codurance.sessionize.sessionizeservice.pairing.service.PairingsService;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.json.webtoken.JsonWebSignature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.security.GeneralSecurityException;

import static org.mockito.Mockito.*;

class PairingsControllerShould {

    private PairingsService mockPairingService;
    private PairingsController controller;
    private TokenVerification tokenVerification;
    private SlackClient slackClient;

    @BeforeEach
    public void setup() {
        mockPairingService = mock(PairingsService.class);
        slackClient = mock(SlackClient.class);
        tokenVerification = mock(TokenVerification.class);
        controller = new PairingsController(mockPairingService, tokenVerification, slackClient);

    }

    @Test
    void return_pairing_on_get_request() throws GeneralSecurityException, IOException {

        when(tokenVerification.verifyGoogleIdToken("123")).thenReturn(prepareIdToken());

        controller.getPairings("123", "sophie.biber@codurance.com");

        verify(mockPairingService, times(1))
          .getPairings("sophie.biber@codurance.com");

    }

    @Test
    void return_pairing_by_status() {

        controller.getPairingsByStatus("sophie.biber@codurance.com", Status.PENDING);

        verify(mockPairingService, times(1))
          .getPairingsBy(Status.PENDING, "sophie.biber@codurance.com");

    }

    GoogleIdToken prepareIdToken() {
        JsonWebSignature.Header header = new JsonWebSignature.Header();
        GoogleIdToken.Payload payload = new GoogleIdToken.Payload();
        payload.setEmail("foobar@codurance.com");
        payload.set("family_name", "Foo");
        payload.set("given_name", "Bar");
        payload.set("picture", "https://url");
        byte[] signatureBytes = new byte[0];
        byte[] signedContentBytes = new byte[0];
        return new GoogleIdToken(header, payload, signatureBytes, signedContentBytes);
    }

}
