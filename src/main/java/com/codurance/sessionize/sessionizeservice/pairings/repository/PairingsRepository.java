package com.codurance.sessionize.sessionizeservice.pairings.repository;

import com.codurance.sessionize.sessionizeservice.pairings.Pairing;
import com.codurance.sessionize.sessionizeservice.pairings.Status;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PairingsRepository extends MongoRepository<Pairing, String> {

  List<Pairing> findPairingByUsersContaining(String userId);
  List<Pairing> findPairingsByUsersContainsAndStatus(String email, Status status);
}
