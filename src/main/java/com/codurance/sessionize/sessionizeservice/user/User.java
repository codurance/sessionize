package com.codurance.sessionize.sessionizeservice.user;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "users")
public class User {

  @Id
  private String id;
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
}
