package com.codurance.sessionize.sessionizeservice.user;

import com.codurance.sessionize.sessionizeservice.preferences.LanguagesPreferences;
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
  private String slackUser;
  private String email;
  private String pictureURL;
  private String firstName;
  private String lastName;
  private boolean optIn;
  private LanguagesPreferences languagesPreferences;
}
