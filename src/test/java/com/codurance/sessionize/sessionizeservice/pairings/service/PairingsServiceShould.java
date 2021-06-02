package com.codurance.sessionize.sessionizeservice.pairings.service;

import com.codurance.sessionize.sessionizeservice.pairings.Status;
import com.codurance.sessionize.sessionizeservice.pairings.repository.PairingsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class PairingsServiceShould {

  PairingsRepository mockPairingsRepository;
  PairingsService pairingsService;

  @BeforeEach
  void setup() {
    mockPairingsRepository = mock(PairingsRepository.class);
    pairingsService = new PairingsServiceImpl(mockPairingsRepository);

  }

  @Test
  void retrieve_all_pairings_belonging_to_an_user_that_matches_the_pairing_status() {

    String email = "foobar@codurance.com";

    pairingsService.getPairingsBy(Status.PENDING, email);

    verify(mockPairingsRepository, times(1)).findPairingsByUsersContainsAndStatus(email, Status.PENDING);
  }
}
