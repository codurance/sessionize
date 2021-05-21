package com.codurance.sessionize.sessionizeservice.user.service;

import com.codurance.sessionize.sessionizeservice.user.UserDTO;
import com.codurance.sessionize.sessionizeservice.user.WebUserDTO;
import org.springframework.stereotype.Component;

@Component
public interface UserService {

  UserDTO signInOrRegister(WebUserDTO webUserDTO);

}
