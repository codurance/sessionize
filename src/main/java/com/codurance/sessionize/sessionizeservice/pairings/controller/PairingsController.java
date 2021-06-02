package com.codurance.sessionize.sessionizeservice.pairings.controller;

import com.codurance.sessionize.sessionizeservice.pairings.Pairing;
import com.codurance.sessionize.sessionizeservice.pairings.service.PairingsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.codurance.sessionize.sessionizeservice.infrastructure.constants.HttpConstants.AUTH_HEADER;
import static com.codurance.sessionize.sessionizeservice.infrastructure.constants.HttpConstants.PAIRINGS;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class PairingsController {

  private final PairingsService pairingsService;

  public PairingsController(PairingsService pairingsService) {
    this.pairingsService = pairingsService;
  }

  @GetMapping(value = PAIRINGS, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Pairing>> getPairings(@RequestHeader(AUTH_HEADER) String email) {
    List<Pairing> pairings = pairingsService.getPairings(email);
    return new ResponseEntity<>(pairings, HttpStatus.OK);
  }
}
