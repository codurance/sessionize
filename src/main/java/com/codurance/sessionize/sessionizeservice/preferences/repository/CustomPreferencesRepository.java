package com.codurance.sessionize.sessionizeservice.preferences.repository;

import com.codurance.sessionize.sessionizeservice.preferences.LanguagesPreferences;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomPreferencesRepository {

  boolean optOut(String email);
  void saveLanguagesForSlack(LanguagesPreferences languagesPreferences, String user);

}
