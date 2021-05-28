package com.codurance.sessionize.sessionizeservice.preferences.repository;

import com.codurance.sessionize.sessionizeservice.preferences.Language;
import com.codurance.sessionize.sessionizeservice.preferences.LanguagesPreferences;
import com.codurance.sessionize.sessionizeservice.user.User;
import com.codurance.sessionize.sessionizeservice.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PreferencesRepositoryShould {
  CustomPreferencesRepository customPreferencesRepository;
  UserRepository mockUserRepository = mock(UserRepository.class);

  @BeforeEach
  void setup() {
    customPreferencesRepository = new CustomPreferencesRepositoryImpl(mockUserRepository);
  }

  @Test
  void opt_out_user_from_pairings() {
    User user = new User();
    user.setOptIn(true);

    when(mockUserRepository.findUserByEmail("foobar@codurance.com")).thenReturn(user);
    when(mockUserRepository.save(user)).thenReturn(user);

    boolean availability = customPreferencesRepository.changeAvailability("foobar@codurance.com");
    assertFalse(availability);
  }

  @Test
  void opt_in_user_to_pairing() {
    User user = new User();
    user.setOptIn(false);

    when(mockUserRepository.findUserByEmail("foobar@codurance.com")).thenReturn(user);
    when(mockUserRepository.save(user)).thenReturn(user);

    boolean availability = customPreferencesRepository.changeAvailability("foobar@codurance.com");
    assertTrue(availability);
  }

  @Test
  void save_a_slack_users_language_preferences() {
    LanguagesPreferences languagesPreferences = new LanguagesPreferences(
            new Language("RUST", "Rust"),
            new Language("FSHARP", "F#"),
            new Language("VISUALBASIC", "VB"));

    String slackUser = "slackUser";
    User andras = new User();
    when(mockUserRepository.findUserBySlackUser(slackUser)).thenReturn(andras);
    when(mockUserRepository.save(andras)).thenReturn(andras);

    customPreferencesRepository.saveLanguagesForSlack(languagesPreferences, slackUser);

    assertEquals(languagesPreferences, andras.getLanguagesPreferences());
  }

}
