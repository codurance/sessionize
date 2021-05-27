package com.codurance.sessionize.sessionizeservice.preferences.service;

import com.codurance.sessionize.sessionizeservice.preferences.repository.CustomPreferencesRepository;
import com.codurance.sessionize.sessionizeservice.user.User;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;


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


}
