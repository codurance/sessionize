package com.codurance.sessionize.sessionizeservice.user.service;

import com.codurance.sessionize.sessionizeservice.preferences.LanguagesPreferences;
import com.codurance.sessionize.sessionizeservice.user.SlackUserDTO;
import com.codurance.sessionize.sessionizeservice.user.User;
import com.codurance.sessionize.sessionizeservice.user.UserDTO;
import com.codurance.sessionize.sessionizeservice.user.WebUserDTO;
import com.codurance.sessionize.sessionizeservice.user.repository.CustomUserRepository;
import com.codurance.sessionize.sessionizeservice.user.repository.UserRepository;
import org.modelmapper.ModelMapper;
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
    User user = new User();
    UserDTO userDTO = new UserDTO();

    user.map(webUserDTO);
    user.setOptIn(true);

    User persistedUser = customUserRepository.findByEmailOrCreate(user);

    userDTO.map(persistedUser);
    return userDTO;
  }

  @Override
  public void slackRegister(SlackUserDTO slackUserDTO) {
    User user = new User();
    user.map(slackUserDTO);
    user.setLanguagesPreferences(new LanguagesPreferences());
    user.setOptIn(true);
    userRepository.save(user);
  }

  @Override
  public boolean isNewUser(String email) {
    return !userRepository.existsByEmail(email);
  }

  @Override
  public void updateSlackIdFor(SlackUserDTO slackUserDTO) {
    String email = slackUserDTO.getEmail();
    String slackId = slackUserDTO.getSlackUser();

    User existingUser = userRepository.findUserByEmail(email);
    existingUser.setSlackUser(slackId);
    userRepository.save(existingUser);
  }

}
