package com.codurance.sessionize.sessionizeservice.config.authentication;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class AuthenticationController {

  @PostMapping("/auth")
  public ResponseEntity authenticate(@RequestBody User user) {

    System.out.println(user.getEmail());

    if (!user.getEmail().isEmpty()) {
     return new ResponseEntity<>(user, HttpStatus.OK);
    }
    return new ResponseEntity(HttpStatus.UNAUTHORIZED);
  }
}
