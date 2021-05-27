package com.codurance.sessionize.sessionizeservice.user.repository;

import com.codurance.sessionize.sessionizeservice.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

  User findUserByEmail(String email);
  User findUserBySlackId(String slackId);
  boolean existsByEmail(String email);


  @Override
  User save(User user);

}
