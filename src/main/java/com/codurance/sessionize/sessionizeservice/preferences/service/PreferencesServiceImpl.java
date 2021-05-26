package com.codurance.sessionize.sessionizeservice.preferences.service;

import com.codurance.sessionize.sessionizeservice.infrastructure.mapper.LanguageMap;
import com.codurance.sessionize.sessionizeservice.preferences.Languages;
import com.codurance.sessionize.sessionizeservice.preferences.LanguagesDTO;
import com.codurance.sessionize.sessionizeservice.preferences.repository.PreferencesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class PreferencesServiceImpl implements PreferencesService {

  private final PreferencesRepository preferencesRepository;
  private final ModelMapper modelMapper;

  @Autowired
  public PreferencesServiceImpl(PreferencesRepository preferencesRepository, ModelMapper modelMapper) {
    this.preferencesRepository = preferencesRepository;
    this.modelMapper = modelMapper;
  }

  @Override
  public boolean optOut(String email) {
    return preferencesRepository.optOut(email);
  }

  @Override
  public Languages setLanguages(LanguagesDTO languagesDTO, String slackUser) {
    modelMapper.addMappings(new LanguageMap());
    return modelMapper.map(languagesDTO, Languages.class);
  }

}
