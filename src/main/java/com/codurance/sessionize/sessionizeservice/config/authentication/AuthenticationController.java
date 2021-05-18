package com.codurance.sessionize.sessionizeservice.config.authentication;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class AuthenticationController {

  private static final String AUTH_HEADER = "Authorization";

  @PostMapping("/auth")
  public ResponseEntity authenticate(@RequestBody User user, @RequestHeader(AUTH_HEADER) String authorizationHeader) {
    if (!user.getEmail().isEmpty()) {
     return new ResponseEntity<>(user, HttpStatus.OK);
    }
    return new ResponseEntity(HttpStatus.UNAUTHORIZED);
  }
}
