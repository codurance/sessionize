package com.codurance.sessionize.sessionizeservice.user.controller;

import com.codurance.sessionize.sessionizeservice.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.codurance.sessionize.sessionizeservice.infrastructure.constants.HttpConstants.OPT_OUT;
import static com.codurance.sessionize.sessionizeservice.infrastructure.constants.HttpConstants.SLACK;

@RestController
@CrossOrigin(origins = "*")
public class PreferencesController {

  UserService userService;

  @Autowired
  public PreferencesController(UserService userService) {
    this.userService = userService;
  }

  @PutMapping(value = {SLACK + OPT_OUT, OPT_OUT})
  public ResponseEntity<HttpStatus> optOut(@RequestParam String email) {
    return userService.optOut(email) ?
      new ResponseEntity<>(HttpStatus.OK) :
      new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
