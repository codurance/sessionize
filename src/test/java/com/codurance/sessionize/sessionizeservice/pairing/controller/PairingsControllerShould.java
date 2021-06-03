package com.codurance.sessionize.sessionizeservice.pairing.controller;

import com.codurance.sessionize.sessionizeservice.matching.service.MatchingService;
import com.codurance.sessionize.sessionizeservice.pairing.Status;
import com.codurance.sessionize.sessionizeservice.pairing.service.PairingsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class PairingsControllerShould {

    private PairingsService mockPairingService;
    private PairingsController controller;
    private MatchingService mockMatchingService;

    @BeforeEach
    public void setup() {
        mockPairingService = mock(PairingsService.class);
        controller = new PairingsController(mockPairingService, mockMatchingService);

    }

    @Test
    void return_pairing_on_get_request() {

        controller.getPairings("sophie.biber@codurance.com");

        verify(mockPairingService, times(1))
          .getPairings("sophie.biber@codurance.com");

    }

    @Test
    void return_pairing_by_status() {

        controller.getPairingsByStatus("sophie.biber@codurance.com", Status.PENDING);

        verify(mockPairingService, times(1))
          .getPairingsBy(Status.PENDING, "sophie.biber@codurance.com");

    }
}
