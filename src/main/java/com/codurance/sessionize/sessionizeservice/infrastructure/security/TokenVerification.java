package com.codurance.sessionize.sessionizeservice.infrastructure.security;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

public class TokenVerification {

  @Value("${GOOGLE_CLIENT_ID}")
  private String googleClientId;

  public GoogleIdToken verifyGoogleIdToken(String token) throws GeneralSecurityException, IOException {
    HttpTransport transport = new NetHttpTransport();
    JsonFactory jsonFactory = new GsonFactory();
    GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
            .setAudience(Collections.singletonList(googleClientId))
            .build();
    return verifier.verify(token);
  }
}
