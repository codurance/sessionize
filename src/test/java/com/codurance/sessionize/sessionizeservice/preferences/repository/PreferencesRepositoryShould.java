package com.codurance.sessionize.sessionizeservice.preferences.repository;

import com.codurance.sessionize.sessionizeservice.user.User;
import com.codurance.sessionize.sessionizeservice.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PreferencesRepositoryShould {


  CustomPreferencesRepository customPreferencesRepository;
  UserRepository mockUserRepository = mock(UserRepository.class);


  @BeforeEach
  void setup() {
    customPreferencesRepository = new CustomPreferencesRepositoryImpl(mockUserRepository);
  }

  @Test
  void opt_out_user_from_pairings() {
    User user = new User();

    when(mockUserRepository.findUserByEmail("foobar@codurance.com")).thenReturn(user);
    when(mockUserRepository.save(user)).thenReturn(user);

    boolean isOptOut = customPreferencesRepository.optOut("foobar@codurance.com");
    assertTrue(isOptOut);
  }

}
