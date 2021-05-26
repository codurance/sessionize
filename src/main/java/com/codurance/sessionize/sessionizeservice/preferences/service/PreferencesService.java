package com.codurance.sessionize.sessionizeservice.preferences.service;

import com.codurance.sessionize.sessionizeservice.preferences.LanguagesDTO;
import org.springframework.stereotype.Service;

@Service
public interface PreferencesService {

  boolean optOut(String email);
  void setLanguages(LanguagesDTO languages, String slackUser);
}
