package com.codurance.sessionize.sessionizeservice.infrastructure.security;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.contract.spec.internal.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

import static com.codurance.sessionize.sessionizeservice.infrastructure.constants.HttpConstants.AUTH_HEADER;

public class TokenVerification implements HandlerInterceptor {

  @Value("${GOOGLE_CLIENT_ID}")
  private String googleClientId;

    @Override
    public boolean
    preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        String token = request.getHeader(AUTH_HEADER);

         try {
           if (isValid(token)) {
             return true;
           }
           response.setStatus(HttpStatus.UNAUTHORIZED);
           return false;
         } catch (Exception e) {
           response.setStatus(HttpStatus.UNAUTHORIZED);
           return false;
         }
    }


    public boolean isValid(String token) throws GeneralSecurityException, IOException {
      GoogleIdToken idToken = verifyGoogleIdToken(token);
      return idToken != null;
    }

  public GoogleIdToken verifyGoogleIdToken(String token) throws GeneralSecurityException, IOException {
    HttpTransport transport = new NetHttpTransport();
    JsonFactory jsonFactory = new GsonFactory();
    GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
            .setAudience(Collections.singletonList(googleClientId))
            .build();
    return verifier.verify(token);
  }
}
