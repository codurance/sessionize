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

  @Override
  public String toString() {
    return "SlackId: " + slackUser + "\n" +
            "email: " + email + "\n" +
            "firstName: " + firstName + "\n" +
            "lastName: " + lastName + "\n" +
            "Languages: \n" + (this.languagesPreferences == null
              ? "null"
              : languagesPreferences.toString()) + "\n";
  }
}
