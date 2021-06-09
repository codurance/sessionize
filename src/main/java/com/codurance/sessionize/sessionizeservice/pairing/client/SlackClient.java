package com.codurance.sessionize.sessionizeservice.pairing.client;

import com.codurance.sessionize.sessionizeservice.matching.service.MatchingService;
import com.codurance.sessionize.sessionizeservice.pairing.Pairing;
import com.codurance.sessionize.sessionizeservice.pairing.Status;
import com.codurance.sessionize.sessionizeservice.pairing.repository.CustomPairingRepository;
import com.codurance.sessionize.sessionizeservice.pairing.repository.PairingsRepository;
import com.codurance.sessionize.sessionizeservice.user.repository.UserRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@Configuration
@EnableScheduling
public class SlackClient {

  private static final String WEEKLY_CRON_SCHEDULE = "0 25 14 * * MON";
  public static final String SLACKBOT_MATCHLIST_URL = "https://sessionize-test-slackbot.codurance.io";
  private static final String EUROPE_LONDON = "Europe/London";
  RestTemplate restTemplate;
  MatchingService matchingService;
  PairingsRepository pairingsRepository;
  CustomPairingRepository customPairingRepository;
  UserRepository userRepository;


  public SlackClient(MatchingService matchingService,
                     PairingsRepository pairingsRepository,
                     UserRepository userRepository,
                     CustomPairingRepository customPairingRepository,
                     RestTemplate restTemplate
  ) {
    this.matchingService = matchingService;
    this.pairingsRepository = pairingsRepository;
    this.userRepository = userRepository;
    this.restTemplate = restTemplate;
    this.customPairingRepository = customPairingRepository;
  }

  @Scheduled(cron = WEEKLY_CRON_SCHEDULE, zone = EUROPE_LONDON)
  public void handleNewSchedule() throws HttpServerErrorException {
    customPairingRepository.updateStatusForAll(Status.PENDING, Status.ARCHIVED);
    List<SlackPairingRequest> slackPairingRequests = generateSlackPairingHttpRequest();
    restTemplate.postForObject(SLACKBOT_MATCHLIST_URL, slackPairingRequests, Void.class);
  }

  private List<SlackPairingRequest> generateSlackPairingHttpRequest() {
    List<Pairing> currentPairings = matchingService.generate();
    return mapAsSlackPairingRequest(currentPairings);
  }

  private List<SlackPairingRequest> mapAsSlackPairingRequest(List<Pairing> pairings) {
    List<SlackPairingRequest> slackPairingRequests = new ArrayList<>();
    pairings.forEach(pairing ->

      slackPairingRequests
        .add(new SlackPairingRequest(pairing.getLanguage(), findSlackIdsFor(pairing), pairing.getStatus())));
    return slackPairingRequests;
  }

  private List<String> findSlackIdsFor(Pairing pairing) {
    List<String> slackUsersIds = new ArrayList<>();
    pairing.getUsers().forEach(user -> slackUsersIds.add(userRepository.findUserByEmail(user).getSlackUser()));
    return slackUsersIds;
  }
}
