package com.codurance.sessionize.sessionizeservice.pairings.service;

import com.codurance.sessionize.sessionizeservice.pairings.PairingsResponse;

import java.io.IOException;
import java.util.List;

public interface PairingsService {
    List<PairingsResponse> generate() throws IOException;
}
