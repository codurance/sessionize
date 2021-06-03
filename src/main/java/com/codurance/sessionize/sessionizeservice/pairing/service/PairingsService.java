package com.codurance.sessionize.sessionizeservice.pairing.service;

import com.codurance.sessionize.sessionizeservice.pairing.Pairing;
import com.codurance.sessionize.sessionizeservice.pairing.PairingDTO;
import com.codurance.sessionize.sessionizeservice.pairing.Status;

import java.util.List;

public interface PairingsService {
    List<Pairing> getPairings(String email);
    List<PairingDTO> getPairingsBy(Status status, String email);
}
