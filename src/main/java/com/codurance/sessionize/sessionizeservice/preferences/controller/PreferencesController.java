package com.codurance.sessionize.sessionizeservice.preferences.controller;

import com.codurance.sessionize.sessionizeservice.preferences.LanguagesDTO;
import com.codurance.sessionize.sessionizeservice.preferences.service.PreferencesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.codurance.sessionize.sessionizeservice.infrastructure.constants.HttpConstants.*;

@RestController
@CrossOrigin(origins = "*")
public class PreferencesController {

    PreferencesService preferencesService;

    @Autowired
    public PreferencesController(PreferencesService preferencesService) {
    this.preferencesService = preferencesService;
  }

  @PutMapping(value = {SLACK + OPT_OUT, OPT_OUT})
  public ResponseEntity<HttpStatus> optOut(@RequestParam String email) {
    return preferencesService.optOut(email) ?
      new ResponseEntity<>(HttpStatus.OK) :
      new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @PutMapping( value = {SLACK + PREFERRED_LANGUAGES}, consumes = MediaType.APPLICATION_JSON_VALUE)
  public void languagePreferences(@RequestHeader("slack-user") String slackUser, @RequestBody LanguagesDTO languages) {
    preferencesService.setLanguages(languages, slackUser);
  }

}
