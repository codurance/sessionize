package com.codurance.sessionize.sessionizeservice.pairings.service;

import com.codurance.sessionize.sessionizeservice.pairings.Pairing;
import com.codurance.sessionize.sessionizeservice.pairings.PairingDTO;
import com.codurance.sessionize.sessionizeservice.pairings.Status;

import java.util.List;

public interface PairingsService {
    List<Pairing> getPairings(String email);
    List<PairingDTO> getPairingsBy(Status status, String email);
}
