package com.codurance.sessionize.sessionizeservice.user;

public interface CustomUserRepository {

  User findByEmailOrCreate(User user);

}
