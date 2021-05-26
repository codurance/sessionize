package com.codurance.sessionize.sessionizeservice.user.service;

import com.codurance.sessionize.sessionizeservice.user.SlackUserDTO;
import com.codurance.sessionize.sessionizeservice.user.UserDTO;
import com.codurance.sessionize.sessionizeservice.user.WebUserDTO;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
  UserDTO webSignInOrRegister(WebUserDTO webUserDTO);
  boolean isNewUser(String email);
  void slackRegister(SlackUserDTO slackUserDTO);
  void updateSlackIdFor(SlackUserDTO slackUserDTO);
}
