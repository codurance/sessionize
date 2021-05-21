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

}
