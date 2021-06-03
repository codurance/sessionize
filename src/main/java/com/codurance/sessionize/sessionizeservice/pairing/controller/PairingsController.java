package com.codurance.sessionize.sessionizeservice.pairing.controller;

import com.codurance.sessionize.sessionizeservice.pairing.Pairing;
import com.codurance.sessionize.sessionizeservice.pairing.PairingDTO;
import com.codurance.sessionize.sessionizeservice.pairing.Status;
import com.codurance.sessionize.sessionizeservice.pairing.service.PairingsService;
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
  public ResponseEntity<List<PairingDTO>> getPairingsByStatus(@RequestParam String email, @RequestParam Status status) {
    List<PairingDTO> pairings = pairingsService.getPairingsBy(status, email);
    return new ResponseEntity<>(pairings, HttpStatus.OK);
  }
}