package com.codurance.sessionize.sessionizeservice.pairing.service;

import com.codurance.sessionize.sessionizeservice.pairing.Status;
import com.codurance.sessionize.sessionizeservice.pairing.repository.PairingsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.mockito.Mockito.*;

public class PairingsServiceShould {

  PairingsRepository mockPairingsRepository;
  PairingsService pairingsService;
  ModelMapper modelMapper;

  @BeforeEach
  void setup() {
    mockPairingsRepository = mock(PairingsRepository.class);
    modelMapper = new ModelMapper();
    pairingsService = new PairingsServiceImpl(mockPairingsRepository, modelMapper);

  }

  @Test
  void retrieve_all_pairings_belonging_to_an_user_that_matches_the_pairing_status() {

    String email = "foobar@codurance.com";

    pairingsService.getPairingsBy(Status.PENDING, email);

    verify(mockPairingsRepository, times(1)).findPairingsByUsersContainsAndStatus(email, Status.PENDING);
  }
}
