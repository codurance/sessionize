package com.codurance.sessionize.sessionizeservice.preferences.service;

import com.codurance.sessionize.sessionizeservice.infrastructure.mapper.LanguageMap;
import com.codurance.sessionize.sessionizeservice.preferences.Language;
import com.codurance.sessionize.sessionizeservice.preferences.LanguagesPreferences;
import com.codurance.sessionize.sessionizeservice.preferences.LanguagesPreferencesDTO;
import com.codurance.sessionize.sessionizeservice.preferences.repository.CustomPreferencesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
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
  public boolean optOut(String email) {
    return customPreferencesRepository.optOut(email);
  }

  @Override
  public void setLanguagesForSlack(LanguagesPreferencesDTO languagesPreferencesDTO, String user) {
    modelMapper.addMappings(new LanguageMap());
    LanguagesPreferences languagesPreferences = modelMapper.map(languagesPreferencesDTO, LanguagesPreferences.class);
    customPreferencesRepository.saveLanguagesForSlack(languagesPreferences, user);
  }

  @Override
  public List<Language> getAvailableLanguages() {
    return Arrays.asList(
      new Language("JAVA", "Java"),
      new Language("CSHARP", "C#"),
      new Language("GOLANG", "Go"),
      new Language("CPP", "C++")
    );
  }
}
