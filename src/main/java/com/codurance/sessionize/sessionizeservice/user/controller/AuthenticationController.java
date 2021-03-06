package com.codurance.sessionize.sessionizeservice.user.controller;

import com.codurance.sessionize.sessionizeservice.infrastructure.security.TokenVerification;
import com.codurance.sessionize.sessionizeservice.user.SlackUserDTO;
import com.codurance.sessionize.sessionizeservice.user.UserDTO;
import com.codurance.sessionize.sessionizeservice.user.WebUserDTO;
import com.codurance.sessionize.sessionizeservice.user.service.UserService;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;

import static com.codurance.sessionize.sessionizeservice.infrastructure.constants.HttpConstants.*;

@RestController
@CrossOrigin(origins = "https://sessionize-coreapi.codurance.io")
public class AuthenticationController {

  private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

  private final TokenVerification tokenVerification;
  private final UserService userService;

  @Autowired
  public AuthenticationController(TokenVerification tokenVerification, UserService userService) {
    this.tokenVerification = tokenVerification;
    this.userService = userService;
  }

  @GetMapping(value = AUTH, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<UserDTO> authenticate(@RequestHeader(AUTH_HEADER) String authorizationHeader) {

    try {
      GoogleIdToken token = tokenVerification.verifyGoogleIdToken(authorizationHeader);
      if (token != null) {
        UserDTO userDTO = userService.webSignInOrRegister(new WebUserDTO(token.getPayload()));
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
      }
      return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    } catch (GeneralSecurityException | IOException | IllegalArgumentException e) {
      LOGGER.error(e.getMessage());
      return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
  }

  @PostMapping(value = SLACK + AUTH, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<UserDTO> authenticate(@RequestBody SlackUserDTO slackUserDTO) {

    if (userService.isNewUser(slackUserDTO.getEmail())) {
      userService.slackRegister(slackUserDTO);
      return new ResponseEntity<>(HttpStatus.CREATED);
    } else {
      userService.handleExistingUserLogin(slackUserDTO);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
  }
}
