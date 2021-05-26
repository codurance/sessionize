package com.codurance.sessionize.sessionizeservice.preferences.repository;

import com.codurance.sessionize.sessionizeservice.preferences.Languages;
import com.codurance.sessionize.sessionizeservice.user.User;
import com.codurance.sessionize.sessionizeservice.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomPreferencesRepositoryImpl implements CustomPreferencesRepository {

  private final UserRepository userRepository;

  @Autowired
  public CustomPreferencesRepositoryImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }


  @Override
  public boolean optOut(String email) {
    User user = userRepository.findUserByEmail(email);
    user.setOptOut(true);
    User updatedUser = userRepository.save(user);
    return updatedUser.isOptOut();
  }

  @Override
  public void saveLanguagesForSlack(Languages languages, String id) {
    User user = userRepository.findUserBySlackId(id);
    user.setLanguages(languages);
    userRepository.save(user);
  }
}
