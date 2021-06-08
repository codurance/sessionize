package com.codurance.sessionize.sessionizeservice.pairing.repository;

import com.codurance.sessionize.sessionizeservice.pairing.Pairing;
import com.codurance.sessionize.sessionizeservice.pairing.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CustomPairingsRepositoryShould {

  PairingsRepository mockPairingsRepository;
  CustomPairingRepository customPairingRepository;

  @BeforeEach
  void setup() {
    this.mockPairingsRepository = mock(PairingsRepository.class);
    this.customPairingRepository = new CustomPairingRepositoryImpl(mockPairingsRepository);
  }


  @Test
  void change_all_pairings_with_a_given_status_to_a_new_status() {
    Pairing stubPairing = new Pairing();
    stubPairing.setStatus(Status.PENDING);
    Pairing stubPairing2 = new Pairing();
    stubPairing.setStatus(Status.PENDING);
    List<Pairing> stubPairings = Arrays.asList(stubPairing, stubPairing2);

    when(mockPairingsRepository.findAllByStatus(Status.PENDING)).thenReturn(stubPairings);
    when(mockPairingsRepository.save(any(Pairing.class))).thenReturn(new Pairing());

    customPairingRepository.updateStatusForAll(Status.PENDING, Status.ARCHIVED);

    verify(mockPairingsRepository, times(2)).save(any(Pairing.class));
    assertEquals(Status.ARCHIVED, stubPairing.getStatus());
    assertEquals(Status.ARCHIVED, stubPairing2.getStatus());
  }
}
