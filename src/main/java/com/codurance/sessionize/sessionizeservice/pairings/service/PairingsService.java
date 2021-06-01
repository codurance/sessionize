package com.codurance.sessionize.sessionizeservice.pairings.service;

import com.codurance.sessionize.sessionizeservice.pairings.Pairing;

import java.util.List;

public interface PairingsService {
    List<Pairing> getPairings(String email);
}
