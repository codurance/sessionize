package com.codurance.sessionize.sessionizeservice.user;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends MongoRepository<User, UUID> {

  User findUserByEmail(String email);
  boolean existsByEmail(String email);

  @Override
  User save(User user);

}
