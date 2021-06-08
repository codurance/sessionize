package com.codurance.sessionize.sessionizeservice.pairing.repository;

import com.codurance.sessionize.sessionizeservice.pairing.Status;

public interface CustomPairingRepository {

  void updateStatusForAll(Status oldStatus, Status newStatus);

}
