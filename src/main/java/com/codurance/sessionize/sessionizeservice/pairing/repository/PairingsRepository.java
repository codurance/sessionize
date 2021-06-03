package com.codurance.sessionize.sessionizeservice.pairing.repository;

import com.codurance.sessionize.sessionizeservice.pairing.Pairing;
import com.codurance.sessionize.sessionizeservice.pairing.PairingDTO;
import com.codurance.sessionize.sessionizeservice.pairing.Status;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PairingsRepository extends MongoRepository<Pairing, String> {

  List<Pairing> findPairingByUsersContaining(String userId);
  List<Pairing> findPairingsByUsersContainsAndStatus(String email, Status status);
  List<Pairing> findAllByStatus(Status status);

  Pairing save(Pairing pairing);
}
