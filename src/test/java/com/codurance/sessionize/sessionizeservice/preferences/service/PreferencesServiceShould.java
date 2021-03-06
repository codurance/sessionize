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

class PreferencesServiceShould {

  CustomPreferencesRepository mockCustomPreferencesRepository = mock(CustomPreferencesRepository.class);
  ModelMapper modelMapper;
  PreferencesService preferencesService = new PreferencesServiceImpl(mockCustomPreferencesRepository, modelMapper);

  @Test
  void opt_out_user() {
    User user = new User();
    user.setEmail("foobar@gmail.com");

    when(mockCustomPreferencesRepository.changeAvailability(user.getEmail())).thenReturn(true);
    assertTrue(preferencesService.changeAvailability("foobar@gmail.com"));
  }

  @Test
  void get_all_available_languages() {

    List<Language> actualLanguages = preferencesService.getAvailableLanguages();
    int size = actualLanguages.size();
    assertEquals(size,32);

  }


}
