package com.codurance.sessionize.sessionizeservice.preferences.repository;

import com.codurance.sessionize.sessionizeservice.preferences.LanguagesPreferences;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserLanguagePreferences {
    String user;
    LanguagesPreferences languagesPreferences;
}
