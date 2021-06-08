package com.codurance.sessionize.sessionizeservice.user.service;

import com.codurance.sessionize.sessionizeservice.preferences.LanguagesPreferences;
import com.codurance.sessionize.sessionizeservice.user.*;
import com.codurance.sessionize.sessionizeservice.user.repository.CustomUserRepository;
import com.codurance.sessionize.sessionizeservice.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  private final CustomUserRepository customUserRepository;
  private final UserRepository userRepository;

  @Autowired
  public UserServiceImpl(CustomUserRepository customUserRepository, UserRepository userRepository) {
    this.customUserRepository = customUserRepository;
    this.userRepository = userRepository;
  }

  @Override
  public UserDTO webSignInOrRegister(WebUserDTO webUserDTO) {
    User user = setInitialPreferences(webUserDTO);
    UserDTO userDTO = new UserDTO();
    User persistedUser = customUserRepository.findByEmailOrCreate(user);
    userDTO.map(persistedUser);
    return userDTO;
  }

  @Override
  public void slackRegister(SlackUserDTO slackUserDTO) {
    User user = setInitialPreferences(slackUserDTO);
    userRepository.save(user);
  }

  private User setInitialPreferences(SlackUserDTO slackUserDTO) {
    User user = new User();
    user.map(slackUserDTO);
    user.setOptIn(true);
    user.setLanguagesPreferences(new LanguagesPreferences());
    return user;
  }

  private User setInitialPreferences(WebUserDTO webUserDTO) {
    User user = new User();
    user.map(webUserDTO);
    user.setOptIn(true);
    user.setLanguagesPreferences(new LanguagesPreferences());
    return user;
  }

  @Override
  public boolean isNewUser(String email) {
    return !userRepository.existsByEmail(email);
  }

  @Override
  public void handleExistingUserLogin(SlackUserDTO slackUserDTO) {

    String email = slackUserDTO.getEmail();
    User existingUser = userRepository.findUserByEmail(email);

    String slackId = slackUserDTO.getSlackUser();
    existingUser.setSlackUser(slackId);
    existingUser.setOptIn(true);

    userRepository.save(existingUser);
  }

}
