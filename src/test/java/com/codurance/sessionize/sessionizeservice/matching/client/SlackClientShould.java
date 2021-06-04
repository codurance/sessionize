package com.codurance.sessionize.sessionizeservice.matching.client;

import com.codurance.sessionize.sessionizeservice.matching.service.MatchingService;
import com.codurance.sessionize.sessionizeservice.pairing.Pairing;
import com.codurance.sessionize.sessionizeservice.pairing.Status;
import com.codurance.sessionize.sessionizeservice.pairing.client.SlackClient;
import com.codurance.sessionize.sessionizeservice.pairing.client.SlackPairingRequest;
import com.codurance.sessionize.sessionizeservice.pairing.repository.PairingsRepository;
import com.codurance.sessionize.sessionizeservice.user.User;
import com.codurance.sessionize.sessionizeservice.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static com.codurance.sessionize.sessionizeservice.pairing.client.SlackClient.SLACKBOT_MATCHLIST_URL;
import static org.mockito.Mockito.*;

public class SlackClientShould {

  RestTemplate mockRestTemplate = mock(RestTemplate.class);
  MatchingService mockMacthingService = mock(MatchingService.class);
  PairingsRepository mockPairingsRepository = mock(PairingsRepository.class);
  UserRepository mockUserRepository = mock(UserRepository.class);
  SlackClient slackClient = new SlackClient(mockMacthingService, mockPairingsRepository, mockUserRepository, mockRestTemplate);

  @Test
  void get_matches_from_azure_and_send_it_to_slackbot() {


    List<Pairing> stubPairings = new ArrayList<>();
    List<SlackPairingRequest> slackPairingRequests = new ArrayList<>();

    when(mockPairingsRepository.findAllByStatus(Status.PENDING)).thenReturn(stubPairings);
    when(mockUserRepository.findUserByEmail("foo")).thenReturn(new User());
    slackClient.pushNewPairings();

    verify(mockMacthingService, times(1)).generate();
    verify(mockRestTemplate, times(1)).postForObject(SLACKBOT_MATCHLIST_URL, slackPairingRequests, Void.class);
  }

}
