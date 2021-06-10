package com.codurance.sessionize.sessionizeservice.pairing.repository;

import com.codurance.sessionize.sessionizeservice.pairing.Status;

import java.util.List;

public interface CustomPairingRepository {

  void updateStatusForAll(List<Status> oldStatuses, Status newStatus);

}
