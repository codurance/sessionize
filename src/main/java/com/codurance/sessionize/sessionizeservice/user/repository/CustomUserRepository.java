package com.codurance.sessionize.sessionizeservice.user.repository;

import com.codurance.sessionize.sessionizeservice.user.User;

public interface CustomUserRepository {

  User findByEmailOrCreate(User user);

}
