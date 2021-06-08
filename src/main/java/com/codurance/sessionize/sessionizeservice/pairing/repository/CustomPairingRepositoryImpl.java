package com.codurance.sessionize.sessionizeservice.pairing.repository;

import com.codurance.sessionize.sessionizeservice.pairing.Pairing;
import com.codurance.sessionize.sessionizeservice.pairing.Status;

import java.util.List;

public class CustomPairingRepositoryImpl implements CustomPairingRepository {

  PairingsRepository pairingsRepository;

  public CustomPairingRepositoryImpl(PairingsRepository pairingsRepository) {
    this.pairingsRepository = pairingsRepository;
  }

  @Override
  public void updateStatusForAll(Status oldStatus, Status newStatus) {
    List<Pairing> toUpdate = pairingsRepository.findAllByStatus(oldStatus);
    toUpdate.forEach(pairing -> pairing.setStatus(newStatus));
    toUpdate.forEach(pairingsRepository::save);
  }
}
