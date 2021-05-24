package com.codurance.sessionize.sessionizeservice.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import lombok.Getter;
import lombok.Setter;

/*
DTO for receiving the user from the sessionize-frontend web interface
 */
@Getter
@Setter
@JsonIgnoreProperties
public class WebUserDTO {

  private String email;
  private String pictureURL;
  private String firstName;
  private String lastName;
  private boolean optOut;

  public WebUserDTO(GoogleIdToken.Payload payload) {
    this.email = payload.getEmail();
    this.pictureURL = (String) payload.get("picture");
    this.firstName = (String) payload.get("given_name");
    this.lastName = (String) payload.get("family_name");
    this.optOut = false;
  }
}
