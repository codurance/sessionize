package com.codurance.sessionize.sessionizeservice.authentication;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;


public class User {
  private String email;
  private String pictureURL;
  private String firstName;
  private String lastName;

  public User(GoogleIdToken.Payload payload) {
    this.email = payload.getEmail();
    this.pictureURL = (String) payload.get("picture");
    this.firstName = (String) payload.get("given_name");
    this.lastName = (String) payload.get("family_name");
  }

  public String getEmail() {
    return email;
  }

  public String getPictureURL() {
    return pictureURL;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }
}
