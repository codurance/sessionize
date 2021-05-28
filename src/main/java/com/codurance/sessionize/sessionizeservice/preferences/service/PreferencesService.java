package com.codurance.sessionize.sessionizeservice.preferences.service;

import com.codurance.sessionize.sessionizeservice.preferences.Language;
import com.codurance.sessionize.sessionizeservice.preferences.LanguagesPreferencesDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PreferencesService {

  boolean optOut(String email);
  void setLanguagesForSlack(LanguagesPreferencesDTO languages, String slackUser);
  List<Language> getAvailableLanguages();
}
