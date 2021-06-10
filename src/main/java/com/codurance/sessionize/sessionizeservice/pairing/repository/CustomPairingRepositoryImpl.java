package com.codurance.sessionize.sessionizeservice.pairing.repository;

import com.codurance.sessionize.sessionizeservice.pairing.Pairing;
import com.codurance.sessionize.sessionizeservice.pairing.Status;

import java.util.ArrayList;
import java.util.List;

public class CustomPairingRepositoryImpl implements CustomPairingRepository {

  PairingsRepository pairingsRepository;

  public CustomPairingRepositoryImpl(PairingsRepository pairingsRepository) {
    this.pairingsRepository = pairingsRepository;
  }

  @Override
  public void updateStatusForAll(List<Status> oldStatuses, Status newStatus) {

    List<Pairing> toUpdate = new ArrayList<>();

    for (Status s: oldStatuses) {
      toUpdate.addAll(pairingsRepository.findAllByStatus(s));
    }

    if (!toUpdate.isEmpty()) {
      toUpdate.forEach(pairing -> pairing.setStatus(newStatus));
      toUpdate.forEach(pairingsRepository::save);
    }

  }
}
