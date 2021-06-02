package com.codurance.sessionize.sessionizeservice.pairings.controller;

import com.codurance.sessionize.sessionizeservice.pairings.Pairing;
import com.codurance.sessionize.sessionizeservice.pairings.Status;
import com.codurance.sessionize.sessionizeservice.pairings.service.PairingsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.codurance.sessionize.sessionizeservice.infrastructure.constants.HttpConstants.*;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class PairingsController {

  private final PairingsService pairingsService;

  public PairingsController(PairingsService pairingsService) {
    this.pairingsService = pairingsService;
  }

  @GetMapping(value = PAIRINGS, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Pairing>> getPairings(@RequestHeader(AUTH_HEADER) @RequestParam String email) {
    List<Pairing> pairings = pairingsService.getPairings(email);
    return new ResponseEntity<>(pairings, HttpStatus.OK);
  }

  @GetMapping(value = SLACK + PAIRINGS, params = {"email"}, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Pairing>> getPairingsForSlack(@RequestParam String email) {
    List<Pairing> pairings = pairingsService.getPairings(email);

    return new ResponseEntity<>(pairings, HttpStatus.OK);
  }

  @GetMapping(value = SLACK + PAIRINGS, params = {"email", "status"}, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Pairing>> getPairingsByStatus(@RequestParam String email, @RequestParam Status status) {
    //get upcoming/current pairing
    //map domain to DTO
    List<Pairing> pairings = pairingsService.getPairingsBy(status, email);
    return new ResponseEntity<>(pairings, HttpStatus.OK);
  }
}
