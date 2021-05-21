package com.codurance.sessionize.sessionizeservice.user;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "users")
public class User {

  @Id
  private UUID   id;
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
