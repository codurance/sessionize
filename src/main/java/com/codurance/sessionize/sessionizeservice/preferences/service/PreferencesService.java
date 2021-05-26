package com.codurance.sessionize.sessionizeservice.preferences.service;

import org.springframework.stereotype.Service;

@Service
public interface PreferencesService {

  boolean optOut(String email);

}
