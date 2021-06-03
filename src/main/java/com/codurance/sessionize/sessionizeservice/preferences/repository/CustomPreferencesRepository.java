package com.codurance.sessionize.sessionizeservice.preferences.repository;

import com.codurance.sessionize.sessionizeservice.preferences.LanguagesPreferences;
import com.codurance.sessionize.sessionizeservice.preferences.UserLanguagePreferences;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomPreferencesRepository {

  boolean changeAvailability(String email);
  void saveLanguagesForSlack(LanguagesPreferences languagesPreferences, String user);
  List<UserLanguagePreferences> getUserLanguagePreferences();
}
