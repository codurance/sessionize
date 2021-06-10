package com.codurance.sessionize.sessionizeservice.matching.client;

import com.codurance.sessionize.sessionizeservice.preferences.UserLanguagePreferences;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserLanguagePreferencesRequest {

  String user;
  AzureLanguagePreferencesRequest preferences;

  public UserLanguagePreferencesRequest(UserLanguagePreferences userLanguagePreferences) {
    this.user = userLanguagePreferences.getUser();

    if (userLanguagePreferences.getLanguagesPreferences().getPrimary() == null) {
      this.preferences = new AzureLanguagePreferencesRequest();
    } else {
      this.preferences = new AzureLanguagePreferencesRequest(
        userLanguagePreferences.getLanguagesPreferences().getPrimary().getValue(),
        userLanguagePreferences.getLanguagesPreferences().getSecondary().getValue(),
        userLanguagePreferences.getLanguagesPreferences().getTertiary().getValue()
      );
    }

  }
}
