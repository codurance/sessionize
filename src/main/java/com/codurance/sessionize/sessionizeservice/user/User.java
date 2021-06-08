package com.codurance.sessionize.sessionizeservice.user;

import com.codurance.sessionize.sessionizeservice.preferences.LanguagesPreferences;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
public class User {
  @Id
  private String id;
  private String slackUser;
  private String email;
  private String pictureURL;
  private String firstName;
  private String lastName;
  private boolean optIn;
  private LanguagesPreferences languagesPreferences;


  public void map(WebUserDTO webUserDTO) {
    this.email = webUserDTO.getEmail();
    this.firstName = webUserDTO.getFirstName();
    this.lastName = webUserDTO.getLastName();
    this.pictureURL = webUserDTO.getPictureURL();
  }

  public void map(SlackUserDTO slackUserDTO) {
    this.email = slackUserDTO.getEmail();
    this.firstName = slackUserDTO.getFirstName();
    this.lastName = slackUserDTO.getLastName();
    this.slackUser = slackUserDTO.getSlackUser();
  }
}
