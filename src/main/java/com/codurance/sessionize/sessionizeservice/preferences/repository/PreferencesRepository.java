package com.codurance.sessionize.sessionizeservice.preferences.repository;

import com.codurance.sessionize.sessionizeservice.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PreferencesRepository extends MongoRepository<User, String> {
//  @Query(value="{}", fields="{ 'email' : 1, 'languagesPreferences' : {'$ne': null}}")
}
// move this to user repo