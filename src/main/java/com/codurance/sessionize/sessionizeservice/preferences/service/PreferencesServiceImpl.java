package com.codurance.sessionize.sessionizeservice.preferences.service;

import com.codurance.sessionize.sessionizeservice.preferences.AvailableLanguages;
import com.codurance.sessionize.sessionizeservice.preferences.Language;
import com.codurance.sessionize.sessionizeservice.preferences.LanguagesPreferences;
import com.codurance.sessionize.sessionizeservice.preferences.LanguagesPreferencesDTO;
import com.codurance.sessionize.sessionizeservice.preferences.repository.CustomPreferencesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PreferencesServiceImpl implements PreferencesService {

  private final CustomPreferencesRepository customPreferencesRepository;
  private final ModelMapper modelMapper;

  @Autowired
  public PreferencesServiceImpl(CustomPreferencesRepository customPreferencesRepository, ModelMapper modelMapper) {
    this.customPreferencesRepository = customPreferencesRepository;
    this.modelMapper = modelMapper;
  }

  @Override
  public boolean changeAvailability(String email) {
    return customPreferencesRepository.changeAvailability(email);
  }

  @Override
  public void setLanguagesForSlack(LanguagesPreferencesDTO languagesPreferencesDTO, String user) {
    LanguagesPreferences languagesPreferences = modelMapper.map(languagesPreferencesDTO, LanguagesPreferences.class);
    customPreferencesRepository.saveLanguagesForSlack(languagesPreferences, user);
  }

  @Override
  public List<Language> getAvailableLanguages() {
    return AvailableLanguages.get();
  }
}
