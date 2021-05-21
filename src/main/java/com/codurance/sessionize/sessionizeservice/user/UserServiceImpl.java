package com.codurance.sessionize.sessionizeservice.user;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  private CustomUserRepository customUserRepository;

  @Autowired
  public UserServiceImpl(CustomUserRepository customUserRepository) {
    this.customUserRepository = customUserRepository;
  }

  @Override
  public UserDTO signInOrRegister(WebUserDTO webUserDTO) {
    ModelMapper mapper = new ModelMapper();
    User user = mapper.map(webUserDTO, User.class);
    User persistedUser = customUserRepository.findByEmailOrCreate(user);
    return mapper.map(persistedUser, UserDTO.class);
  }
}
