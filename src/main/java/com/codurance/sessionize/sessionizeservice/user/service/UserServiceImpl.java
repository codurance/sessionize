package com.codurance.sessionize.sessionizeservice.user.service;

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
  private final ModelMapper mapper;

  @Autowired
  public UserServiceImpl(CustomUserRepository customUserRepository, UserRepository userRepository, ModelMapper mapper) {
    this.customUserRepository = customUserRepository;
    this.userRepository = userRepository;
    this.mapper = mapper;
  }

  @Override
  public UserDTO webSignInOrRegister(WebUserDTO webUserDTO) {
    User user = mapper.map(webUserDTO, User.class);
    User persistedUser = customUserRepository.findByEmailOrCreate(user);
    return mapper.map(persistedUser, UserDTO.class);
  }

  @Override
  public void slackRegister(SlackUserDTO slackUserDTO) {
    User user = mapper.map(slackUserDTO, User.class);
    user.setOptOut(false);
    userRepository.save(user);
  }

  @Override
  public boolean isNewUser(String email) {
    return !userRepository.existsByEmail(email);
  }

  @Override
  public void updateSlackIdFor(SlackUserDTO slackUserDTO) {

    String email = slackUserDTO.getEmail();
    String slackId = slackUserDTO.getSlackId();

    User existingUser = userRepository.findUserByEmail(email);
    existingUser.setSlackId(slackId);
    userRepository.save(existingUser);
  }

}
