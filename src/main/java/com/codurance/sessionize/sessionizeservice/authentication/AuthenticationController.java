package com.codurance.sessionize.sessionizeservice.authentication;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;

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
  public void authenticate(@RequestHeader(AUTH_HEADER) String authorizationHeader) {
    //TODO: this controller need to be converted to user controller, we don't need to check for authentication directly anymore

  }

}
