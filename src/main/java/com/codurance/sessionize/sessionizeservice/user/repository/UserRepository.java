package com.codurance.sessionize.sessionizeservice.user.repository;

import com.codurance.sessionize.sessionizeservice.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

  List<User> findByLanguagesPreferencesIsNotNull();
  User findUserByEmail(String email);
  User findUserBySlackUser(String slackUser);
  boolean existsByEmail(String email);

  @Override
  User save(User user);
}
