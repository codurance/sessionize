package com.codurance.sessionize.sessionizeservice.config.authentication;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.codurance.sessionize.sessionizeservice.config.utils.Constants.AUTH_HEADER;
import static com.codurance.sessionize.sessionizeservice.config.utils.Constants.AUTH_URL;

@RestController
@CrossOrigin("*")
public class AuthenticationController {

  @PostMapping(AUTH_URL)
  public ResponseEntity authenticate(@RequestBody User user, @RequestHeader(AUTH_HEADER) String authorizationHeader) {
    if (!user.getEmail().isEmpty()) {
     return new ResponseEntity<>(user, HttpStatus.OK);
    }
    return new ResponseEntity(HttpStatus.UNAUTHORIZED);
  }
}
