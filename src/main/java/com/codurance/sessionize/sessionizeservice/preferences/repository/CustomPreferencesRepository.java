package com.codurance.sessionize.sessionizeservice.preferences.repository;

import com.codurance.sessionize.sessionizeservice.preferences.Languages;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomPreferencesRepository {

  boolean optOut(String email);
  void saveLanguagesForSlack( Languages languages, String user);

}
