package com.codurance.sessionize.sessionizeservice.preferences.service;

import com.codurance.sessionize.sessionizeservice.preferences.repository.PreferencesRepository;
import com.codurance.sessionize.sessionizeservice.user.User;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PreferencesServiceShould {

  PreferencesRepository mockPreferencesRepository = mock(PreferencesRepository.class);
  PreferencesService preferencesService = new PreferencesServiceImpl(mockPreferencesRepository);

  @Test
  void opt_out_user() {
    User user = new User();
    user.setEmail("foobar@gmail.com");

    when(mockPreferencesRepository.optOut(user.getEmail())).thenReturn(true);
    assertTrue(preferencesService.optOut("foobar@gmail.com"));
  }

}
