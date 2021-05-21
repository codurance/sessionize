package com.codurance.sessionize.sessionizeservice.authentication;

import com.codurance.sessionize.sessionizeservice.user.WebUserDTO;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.codurance.sessionize.sessionizeservice.utils.Constants.AUTH_HEADER;
import static com.codurance.sessionize.sessionizeservice.utils.Constants.AUTH_URL;

@RestController
@CrossOrigin(origins = "*")
public class AuthenticationController {

  TokenVerification tokenVerification;
  private static Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

  public AuthenticationController(TokenVerification tokenVerification) {
    this.tokenVerification = tokenVerification;
  }


  @GetMapping(value = AUTH_URL, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<WebUserDTO> authenticate(@RequestHeader(AUTH_HEADER) String authorizationHeader) {

    //TODO: swap over with service layer

    try {
      GoogleIdToken token = tokenVerification.verifyGoogleIdToken(authorizationHeader);
      boolean isValid = token != null;
      if (isValid) {
        WebUserDTO user = new WebUserDTO(token.getPayload());
        ResponseEntity entity = new ResponseEntity(user, HttpStatus.OK);
        return entity;
      }

      return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    } catch (Exception e) {
      logger.error(e.getMessage());
      return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }
  }
}
