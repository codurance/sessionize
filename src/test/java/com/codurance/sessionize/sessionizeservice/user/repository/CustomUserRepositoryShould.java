package com.codurance.sessionize.sessionizeservice.user.repository;

import com.codurance.sessionize.sessionizeservice.user.User;
import org.junit.jupiter.api.Test;


import static org.mockito.Mockito.*;

public class CustomUserRepositoryShould {


  UserRepository userRepository = mock(UserRepository.class);
  CustomUserRepository customUserRepository = new CustomUserRepositoryImpl(userRepository);

  @Test
  public void not_save_already_existing_user() {
    User incomingUser = new User("testy@mctestface.com", "foo", "bar", "baz");

    when(userRepository.existsByEmail(incomingUser.getEmail())).thenReturn(true);
    when(userRepository.findUserByEmail(incomingUser.getEmail())).thenReturn(incomingUser);
    customUserRepository.findByEmailOrCreate(incomingUser);

    verify(userRepository, times(1)).existsByEmail(incomingUser.getEmail());
    verify(userRepository, times(1)).findUserByEmail(incomingUser.getEmail());
    verify(userRepository, times(0)).save(incomingUser);
  }

  @Test
  public void save_if_user_doesnt_exist() {
    User incomingUser = new User("testy@mctestface.com", "foo", "bar", "baz");

    when(userRepository.existsByEmail(incomingUser.getEmail())).thenReturn(false);
    when(userRepository.findUserByEmail(incomingUser.getEmail())).thenReturn(incomingUser);
    customUserRepository.findByEmailOrCreate(incomingUser);

    verify(userRepository, times(1)).existsByEmail(incomingUser.getEmail());
    verify(userRepository, times(0)).findUserByEmail(incomingUser.getEmail());
    verify(userRepository, times(1)).save(incomingUser);
  }

}
