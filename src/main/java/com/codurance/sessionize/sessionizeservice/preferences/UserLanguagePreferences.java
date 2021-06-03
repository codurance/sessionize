package com.codurance.sessionize.sessionizeservice.preferences;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserLanguagePreferences {
    String user;
    LanguagesPreferences languagesPreferences;
}
