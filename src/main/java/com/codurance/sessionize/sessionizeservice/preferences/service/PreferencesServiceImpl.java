package com.codurance.sessionize.sessionizeservice.preferences.service;

import com.codurance.sessionize.sessionizeservice.infrastructure.mapper.LanguageMap;
import com.codurance.sessionize.sessionizeservice.preferences.Languages;
import com.codurance.sessionize.sessionizeservice.preferences.LanguagesDTO;
import com.codurance.sessionize.sessionizeservice.preferences.repository.CustomPreferencesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class PreferencesServiceImpl implements PreferencesService {

  private final CustomPreferencesRepository customPreferencesRepository;
  private final ModelMapper modelMapper;

  @Autowired
  public PreferencesServiceImpl(CustomPreferencesRepository customPreferencesRepository, ModelMapper modelMapper) {
    this.customPreferencesRepository = customPreferencesRepository;
    this.modelMapper = modelMapper;
  }

  @Override
  public boolean optOut(String email) {
    return customPreferencesRepository.optOut(email);
  }

  @Override
  public void setLanguages(LanguagesDTO languagesDTO, String user) {
    modelMapper.addMappings(new LanguageMap());
    Languages languages = modelMapper.map(languagesDTO, Languages.class);
    customPreferencesRepository.saveLanguagesForSlack(languages, user);
  }
}
