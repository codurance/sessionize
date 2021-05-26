package com.codurance.sessionize.sessionizeservice.preferences.service;

import com.codurance.sessionize.sessionizeservice.preferences.repository.PreferencesRepository;
import org.springframework.stereotype.Service;

@Service
public class PreferencesServiceImpl implements PreferencesService {

  private final PreferencesRepository preferencesRepository;

  public PreferencesServiceImpl(PreferencesRepository preferencesRepository) {
    this.preferencesRepository = preferencesRepository;
  }

  @Override
  public boolean optOut(String email) {
    return preferencesRepository.optOut(email);
  }

}
