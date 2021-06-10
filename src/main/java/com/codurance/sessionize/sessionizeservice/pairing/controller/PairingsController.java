package com.codurance.sessionize.sessionizeservice.pairing.controller;

import com.codurance.sessionize.sessionizeservice.infrastructure.security.TokenVerification;
import com.codurance.sessionize.sessionizeservice.pairing.Pairing;
import com.codurance.sessionize.sessionizeservice.pairing.PairingDTO;
import com.codurance.sessionize.sessionizeservice.pairing.Status;
import com.codurance.sessionize.sessionizeservice.pairing.client.SlackClient;
import com.codurance.sessionize.sessionizeservice.pairing.service.PairingsService;
import com.codurance.sessionize.sessionizeservice.user.UserDTO;
import com.codurance.sessionize.sessionizeservice.user.WebUserDTO;
import com.codurance.sessionize.sessionizeservice.user.controller.AuthenticationController;
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
import java.util.List;

import static com.codurance.sessionize.sessionizeservice.infrastructure.constants.HttpConstants.*;


@RestController
@CrossOrigin(origins = "https://sessionize.codurance.io/")
public class PairingsController {

  private static final Logger LOGGER = LoggerFactory.getLogger(PairingsController.class);

  private final PairingsService pairingsService;
  private final TokenVerification tokenVerification;
  SlackClient slackClient;

  @Autowired
  public PairingsController(PairingsService pairingsService, TokenVerification tokenVerification, SlackClient slackClient) {
    this.pairingsService = pairingsService;
    this.tokenVerification = tokenVerification;
    this.slackClient = slackClient;
  }

  @GetMapping(value = PAIRINGS, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Pairing>> getPairings(@RequestHeader(AUTH_HEADER) String authorizationHeader, @RequestParam String email) {

    try {
      GoogleIdToken token = tokenVerification.verifyGoogleIdToken(authorizationHeader);
      if (token != null) {
        List<Pairing> pairings = pairingsService.getPairings(email);
        return new ResponseEntity<>(pairings, HttpStatus.OK);
      }
      return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    } catch (GeneralSecurityException | IOException | IllegalArgumentException e) {
      LOGGER.error(e.getMessage());
      return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }


  }

  @GetMapping(value = SLACK + PAIRINGS, params = {"email"}, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Pairing>> getPairingsForSlack(@RequestParam String email) {
    List<Pairing> pairings = pairingsService.getPairings(email);

    return new ResponseEntity<>(pairings, HttpStatus.OK);
  }

  @GetMapping(value = SLACK + PAIRINGS, params = {"email", "status"}, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<PairingDTO>> getPairingsByStatus(@RequestParam String email, @RequestParam Status status) {
    List<PairingDTO> pairings = pairingsService.getPairingsBy(status, email);
    return new ResponseEntity<>(pairings, HttpStatus.OK);
  }


  //this endpoint is for testing, delete when scheduled jobs implemented
  @GetMapping(value = "/test-matching")
  public void test() {
    slackClient.handleNewSchedule();
  }

}
