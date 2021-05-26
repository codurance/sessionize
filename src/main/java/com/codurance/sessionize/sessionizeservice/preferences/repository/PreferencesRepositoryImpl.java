package com.codurance.sessionize.sessionizeservice.preferences.repository;

import com.codurance.sessionize.sessionizeservice.user.User;
import com.codurance.sessionize.sessionizeservice.user.repository.UserRepository;

public class PreferencesRepositoryImpl implements PreferencesRepository {

  final private UserRepository userRepository;

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
