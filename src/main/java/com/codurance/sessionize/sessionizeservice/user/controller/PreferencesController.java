package com.codurance.sessionize.sessionizeservice.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.codurance.sessionize.sessionizeservice.infrastructure.constants.HttpConstants.OPT_OUT;
import static com.codurance.sessionize.sessionizeservice.infrastructure.constants.HttpConstants.SLACK;

@RestController
@CrossOrigin(origins = "*")
public class PreferencesController {

  @PutMapping(value = SLACK + OPT_OUT)
  public ResponseEntity optOut(@RequestParam String email) {
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
