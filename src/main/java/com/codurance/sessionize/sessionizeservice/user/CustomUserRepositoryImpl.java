package com.codurance.sessionize.sessionizeservice.user;

public class CustomUserRepositoryImpl implements CustomUserRepository {

  UserRepository userRepository;

  public CustomUserRepositoryImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public User findByEmailOrCreate(User user) {
    String email = user.getEmail();
    if (!userRepository.existsByEmail(email)) {
      return userRepository.save(user);
    }
    return userRepository.findUserByEmail(email);
  }
}
