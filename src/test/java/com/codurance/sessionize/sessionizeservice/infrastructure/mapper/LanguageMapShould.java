package com.codurance.sessionize.sessionizeservice.infrastructure.mapper;

import com.codurance.sessionize.sessionizeservice.preferences.Language;
import com.codurance.sessionize.sessionizeservice.preferences.LanguagesPreferences;
import com.codurance.sessionize.sessionizeservice.preferences.LanguagesPreferencesDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LanguageMapShould {

  private ModelMapper modelMapper;

  @BeforeEach
  public void setup() {
    modelMapper = new ModelMapper();
    modelMapper.addMappings(new LanguageMap());
  }

  @Test
  void validate_if_language_mapping_object_is_valid() {

    LanguagesPreferencesDTO languagesPreferencesDTO = prepateLanguagesDTO();
    LanguagesPreferences languagesPreferences = modelMapper.map(languagesPreferencesDTO, LanguagesPreferences.class);

    modelMapper.validate();

    assertEquals(languagesPreferencesDTO.getPrimaryLanguage(), languagesPreferences.getPrimary());
    assertEquals(languagesPreferencesDTO.getSecondaryLanguage(), languagesPreferences.getSecondary());
    assertEquals(languagesPreferencesDTO.getTertiaryLanguage(), languagesPreferences.getTertiary());

  }

  private LanguagesPreferencesDTO prepateLanguagesDTO() {
    LanguagesPreferencesDTO languagesPreferencesDTO = new LanguagesPreferencesDTO();
    languagesPreferencesDTO.setPrimaryLanguage(new Language("FOO", "Foo"));
    languagesPreferencesDTO.setSecondaryLanguage(new Language("BAR", "Bar"));
    languagesPreferencesDTO.setTertiaryLanguage(new Language("BAZ", "Baz"));
    return languagesPreferencesDTO;
  }


}
