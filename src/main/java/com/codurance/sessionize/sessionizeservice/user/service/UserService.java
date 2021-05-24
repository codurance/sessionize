package com.codurance.sessionize.sessionizeservice.user.service;

import com.codurance.sessionize.sessionizeservice.user.SlackUserDTO;
import com.codurance.sessionize.sessionizeservice.user.UserDTO;
import com.codurance.sessionize.sessionizeservice.user.WebUserDTO;
import org.springframework.stereotype.Component;

@Component
public interface UserService {

  UserDTO webSignInOrRegister(WebUserDTO webUserDTO);
  void slackRegister(SlackUserDTO slackUserDTO);
  boolean isNewUser(String email);
  void updateSlackIdFor(SlackUserDTO slackUserDTO);

}
