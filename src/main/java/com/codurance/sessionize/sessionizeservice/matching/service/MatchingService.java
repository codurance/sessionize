package com.codurance.sessionize.sessionizeservice.matching.service;

import com.codurance.sessionize.sessionizeservice.matching.MatchesDTO;
import com.codurance.sessionize.sessionizeservice.pairing.Pairing;

import java.io.IOException;
import java.util.List;

public interface MatchingService {
    MatchesDTO getMatches() throws IOException;
    List<Pairing> mapToPairing(MatchesDTO matchesDTO);
    void generate();
}
