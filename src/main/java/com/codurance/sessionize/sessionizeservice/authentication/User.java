package com.codurance.sessionize.sessionizeservice.authentication;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


public class User {
  private String email;
  private String pictureURL;
  private String firstName;
  private String lastName;

  public User(String email, String pictureURL, String firstName, String lastName) {
    this.email = email;
    this.pictureURL = pictureURL;
    this.firstName = firstName;
    this.lastName = lastName;
  }

}
