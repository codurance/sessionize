package com.codurance.sessionize.sessionizeservice.preferences.service;

import com.codurance.sessionize.sessionizeservice.preferences.LanguagesPreferencesDTO;
import org.springframework.stereotype.Service;

@Service
public interface PreferencesService {

  boolean optOut(String email);
  void setLanguagesForSlack(LanguagesPreferencesDTO languages, String slackUser);
}
