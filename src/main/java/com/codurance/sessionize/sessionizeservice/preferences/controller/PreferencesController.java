package com.codurance.sessionize.sessionizeservice.preferences.controller;

import com.codurance.sessionize.sessionizeservice.preferences.service.PreferencesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.codurance.sessionize.sessionizeservice.infrastructure.constants.HttpConstants.*;

@RestController
@CrossOrigin(origins = "*")
public class PreferencesController {

  private final PreferencesService preferencesService;

  public PreferencesController(PreferencesService preferencesService) {
    this.preferencesService = preferencesService;
  }

  @PutMapping(value = {SLACK + OPT_OUT, OPT_OUT})
  public ResponseEntity<HttpStatus> optOut(@RequestParam String email) {
    return preferencesService.optOut(email) ?
      new ResponseEntity<>(HttpStatus.OK) :
      new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @PutMapping(value =  PREFERRED_LANGUAGES)
  public ResponseEntity<> languagePreferences(@RequestBody String languages) {
    return new ResponseEntity<>();
  }
}
