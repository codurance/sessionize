package com.codurance.sessionize.sessionizeservice.user;

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

  public User() {
  }

  public User(String email, String pictureURL, String firstName, String lastName) {
    this.email = email;
    this.pictureURL = pictureURL;
    this.firstName = firstName;
    this.lastName = lastName;
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
