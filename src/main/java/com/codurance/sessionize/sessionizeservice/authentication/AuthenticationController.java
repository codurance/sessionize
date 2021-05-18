package com.codurance.sessionize.sessionizeservice.authentication;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;

import static com.codurance.sessionize.sessionizeservice.utils.Constants.AUTH_HEADER;
import static com.codurance.sessionize.sessionizeservice.utils.Constants.AUTH_URL;

@RestController
@CrossOrigin("*")
public class AuthenticationController {

  TokenVerification tokenVerification;

  public AuthenticationController(TokenVerification tokenVerification) {
    this.tokenVerification = tokenVerification;
  }


  @GetMapping(AUTH_URL)
  public ResponseEntity authenticate(@RequestHeader(AUTH_HEADER) String authorizationHeader) throws GeneralSecurityException, IOException {

    boolean isValid = tokenVerification.isValid(authorizationHeader);

    if (isValid) {
      return new ResponseEntity<>(HttpStatus.OK);
    }
    return new ResponseEntity(HttpStatus.UNAUTHORIZED);
  }
}
