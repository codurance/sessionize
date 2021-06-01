package com.codurance.sessionize.sessionizeservice.pairings.repository;

import com.codurance.sessionize.sessionizeservice.pairings.Pairing;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PairingsRepository extends MongoRepository<Pairing, String> {

  @Query("{ users: { $in: [ObjectId(?0)] } }")
  List<Pairing> findPairingsByUserId(String userId);
}
