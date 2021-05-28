package com.codurance.sessionize.sessionizeservice.pairings;

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

  private final PairingRepository repository;

  public PairingsController(PairingRepository repository) {
    this.repository = repository;
  }

  @GetMapping(value = PAIRINGS, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Pairing>> getPairings(@RequestHeader(AUTH_HEADER) String authorization) {
    List<Pairing> pairings = repository.getPairings("sophie.biber@codurance.com");
    return new ResponseEntity<List<Pairing>>(pairings, HttpStatus.OK);
  }
}
