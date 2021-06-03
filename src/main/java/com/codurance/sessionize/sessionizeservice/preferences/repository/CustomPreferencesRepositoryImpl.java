package com.codurance.sessionize.sessionizeservice.preferences.repository;

import com.codurance.sessionize.sessionizeservice.preferences.LanguagesPreferences;
import com.codurance.sessionize.sessionizeservice.user.User;
import com.codurance.sessionize.sessionizeservice.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class CustomPreferencesRepositoryImpl implements CustomPreferencesRepository {

  private final UserRepository userRepository;

  @Autowired
  public CustomPreferencesRepositoryImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public boolean changeAvailability(String email) {
    User user = userRepository.findUserByEmail(email);

    if (user.isOptIn()) {
      user.setOptIn(false);
    } else {
      user.setOptIn(true);
    }
    User updatedUser = userRepository.save(user);
    return updatedUser.isOptIn();
  }

  @Override
  public void saveLanguagesForSlack(LanguagesPreferences languagesPreferences, String id) {
    User user = userRepository.findUserBySlackUser(id);
    user.setLanguagesPreferences(languagesPreferences);
    userRepository.save(user);
  }

  @Override
  public List<UserLanguagePreferences> getUserLanguagePreferences() {
    List<User> users = userRepository.getLanguagesPreferences();
    return mapUserLanguagePreferences(users);
  }

  private List<UserLanguagePreferences> mapUserLanguagePreferences(List<User> users) {
    //TODO: this needs to be changed to the following DTO:
    /*
    List<UserPreferences> whenre UP is:
    String user(email)
    Preferences.class
      String pref1
      String pref2
      String pref3
     */
    return users.stream()
            .filter(user -> user.getLanguagesPreferences() != null)
            .map(user -> new UserLanguagePreferences(user.getEmail(), user.getLanguagesPreferences()))
            .collect(Collectors.toList());
  }
}
