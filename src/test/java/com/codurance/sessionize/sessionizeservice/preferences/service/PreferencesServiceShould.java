package com.codurance.sessionize.sessionizeservice.preferences.service;

import com.codurance.sessionize.sessionizeservice.preferences.Language;
import com.codurance.sessionize.sessionizeservice.preferences.repository.CustomPreferencesRepository;
import com.codurance.sessionize.sessionizeservice.user.User;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;


import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PreferencesServiceShould {

  CustomPreferencesRepository mockCustomPreferencesRepository = mock(CustomPreferencesRepository.class);
  ModelMapper modelMapper;
  PreferencesService preferencesService = new PreferencesServiceImpl(mockCustomPreferencesRepository, modelMapper);

  @Test
  void opt_out_user() {
    User user = new User();
    user.setEmail("foobar@gmail.com");

    when(mockCustomPreferencesRepository.optOut(user.getEmail())).thenReturn(true);
    assertTrue(preferencesService.optOut("foobar@gmail.com"));
  }

  @Test
  void get_all_available_languages() {

    List<Language> expectedLanguages = Arrays.asList(
      new Language("JAVA", "Java"),
      new Language("CSHARP", "C#"),
      new Language("GOLANG", "Go"),
      new Language("CPP", "C++")
    );

    List<Language> actualLanguages = preferencesService.getAvailableLanguages();

    assertEquals(expectedLanguages, actualLanguages);

  }


}
