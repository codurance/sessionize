package com.codurance.sessionize.sessionizeservice.preferences.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface PreferencesRepository {

  boolean optOut(String email);

}
