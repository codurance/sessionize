package com.codurance.sessionize.sessionizeservice.user.repository;

import com.codurance.sessionize.sessionizeservice.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

  @Query(value = "{}", fields = "{ 'email' : 1, 'languagesPreferences' : 1}")
  List<User> getLanguagesPreferences();

  User findUserByEmail(String email);
  User findUserBySlackUser(String slackUser);
  boolean existsByEmail(String email);

  @Override
  User save(User user);
}
