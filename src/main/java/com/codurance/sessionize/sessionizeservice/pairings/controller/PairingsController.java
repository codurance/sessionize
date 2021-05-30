package com.codurance.sessionize.sessionizeservice.pairings.controller;

import com.codurance.sessionize.sessionizeservice.pairings.Pairing;
import com.codurance.sessionize.sessionizeservice.pairings.repository.PairingRepository;
import com.codurance.sessionize.sessionizeservice.preferences.repository.CustomPreferencesRepository;
import com.codurance.sessionize.sessionizeservice.preferences.repository.UserLanguagePreferences;
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
  private final CustomPreferencesRepository preferencesRepository;

  public PairingsController(PairingRepository repository, CustomPreferencesRepository preferencesRepository) {
    this.repository = repository;
    this.preferencesRepository = preferencesRepository;
  }

  @GetMapping(value = PAIRINGS, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Pairing>> getPairings(@RequestHeader(AUTH_HEADER) String authorization) {
    List<Pairing> pairings = repository.getPairings("sophie.biber@codurance.com");
    return new ResponseEntity<>(pairings, HttpStatus.OK);
  }

  @GetMapping(value = PAIRINGS + "/match", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<UserLanguagePreferences>> getUserLanguagePreferences() {
    return new ResponseEntity<>(
            preferencesRepository.getUserLanguagePreferences(),
            HttpStatus.OK);
  }
}
