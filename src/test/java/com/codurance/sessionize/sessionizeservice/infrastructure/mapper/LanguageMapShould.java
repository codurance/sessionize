package com.codurance.sessionize.sessionizeservice.infrastructure.mapper;

import com.codurance.sessionize.sessionizeservice.preferences.Languages;
import com.codurance.sessionize.sessionizeservice.preferences.LanguagesDTO;
import org.junit.jupiter.api.BeforeAll;
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

    LanguagesDTO languagesDTO = prepateLanguagesDTO();
    Languages languages = modelMapper.map(languagesDTO, Languages.class);

    modelMapper.validate();

    assertEquals(languagesDTO.getPrimaryLanguage(), languages.getPrimary().getName());
    assertEquals(languagesDTO.getSecondaryLanguage(), languages.getSecondary().getName());
    assertEquals(languagesDTO.getTertiaryLanguage(), languages.getTertiary().getName());

  }

  private LanguagesDTO prepateLanguagesDTO() {
    LanguagesDTO languagesDTO = new LanguagesDTO();
    languagesDTO.setPrimaryLanguage("Foo");
    languagesDTO.setSecondaryLanguage("Bar");
    languagesDTO.setTertiaryLanguage("Baz");
    return languagesDTO;
  }


}
