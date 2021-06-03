package com.codurance.sessionize.sessionizeservice.matching.service;

import com.codurance.sessionize.sessionizeservice.matching.MatchesDTO;

import java.io.IOException;

public interface MatchingService {
    MatchesDTO generate() throws IOException;
    void mapToPairing(MatchesDTO matchesDTO);
}
