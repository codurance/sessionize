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
  private String firstName;
  private boolean optOut;

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPictureURL() {
    return pictureURL;
  }

  public void setPictureURL(String pictureURL) {
    this.pictureURL = pictureURL;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
}
