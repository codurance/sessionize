package com.codurance.sessionize.sessionizeservice.preferences.repository;

import com.codurance.sessionize.sessionizeservice.user.User;
import com.codurance.sessionize.sessionizeservice.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class PreferencesRepositoryImpl implements PreferencesRepository {

  private final UserRepository userRepository;

  @Autowired
  public PreferencesRepositoryImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }


  @Override
  public boolean optOut(String email) {
    User user = userRepository.findUserByEmail(email);
    user.setOptOut(true);
    User updatedUser = userRepository.save(user);
    return updatedUser.isOptOut();
  }
}
