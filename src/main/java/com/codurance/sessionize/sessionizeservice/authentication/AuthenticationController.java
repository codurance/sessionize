package com.codurance.sessionize.sessionizeservice.authentication;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.codurance.sessionize.sessionizeservice.utils.Constants.AUTH_HEADER;
import static com.codurance.sessionize.sessionizeservice.utils.Constants.AUTH_URL;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class AuthenticationController {

  TokenVerification tokenVerification;

  public AuthenticationController(TokenVerification tokenVerification) {
    this.tokenVerification = tokenVerification;
  }


  @GetMapping(AUTH_URL)
  public ResponseEntity authenticate(@RequestHeader(AUTH_HEADER) String authorizationHeader) {
    try {
      return tokenVerification.isValid(authorizationHeader) ? new ResponseEntity(HttpStatus.OK) : new ResponseEntity(HttpStatus.UNAUTHORIZED);
    } catch (Exception e) {
      return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }
  }
}
