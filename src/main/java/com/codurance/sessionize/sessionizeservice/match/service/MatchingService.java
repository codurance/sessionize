package com.codurance.sessionize.sessionizeservice.match.service;

import com.codurance.sessionize.sessionizeservice.pairings.PairingsResponse;

import java.io.IOException;
import java.util.List;

public interface MatchingService {
    List<PairingsResponse> generate() throws IOException;
}
