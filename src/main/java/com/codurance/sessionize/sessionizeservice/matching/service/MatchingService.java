package com.codurance.sessionize.sessionizeservice.matching.service;

import com.codurance.sessionize.sessionizeservice.matching.client.MatchResponse;
import com.codurance.sessionize.sessionizeservice.pairing.Pairing;

import java.io.IOException;
import java.util.List;

public interface MatchingService {
    List<MatchResponse> getMatchesForUserPreferences() throws IOException;
    List<Pairing> mapAsPairing(List<MatchResponse> matches);
    List<Pairing> generate();
}
