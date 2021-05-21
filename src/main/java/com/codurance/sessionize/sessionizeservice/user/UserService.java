package com.codurance.sessionize.sessionizeservice.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public interface UserService {

  UserDTO signInOrRegister(WebUserDTO webUserDTO);

}
