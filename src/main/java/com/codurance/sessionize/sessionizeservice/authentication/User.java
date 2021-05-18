package com.codurance.sessionize.sessionizeservice.authentication;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

  private String email;

  public String getEmail() {
    return email;
  }
}
