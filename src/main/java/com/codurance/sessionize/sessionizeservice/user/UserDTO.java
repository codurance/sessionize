package com.codurance.sessionize.sessionizeservice.user;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO that is returned by the sessionize-servie API
 */
@Getter
@Setter
public class UserDTO {

  private String email;
  private String pictureURL;
  private String name;
  private boolean optIn;


  public void map(User user) {
    this.email = user.getEmail();
    this.pictureURL = user.getPictureURL();
    this.name = user.getName();
    this.optIn = user.isOptIn();
  }
}
