package com.codurance.sessionize.sessionizeservice.pairing.client;

import com.codurance.sessionize.sessionizeservice.matching.service.MatchingService;
import com.codurance.sessionize.sessionizeservice.pairing.Pairing;
import com.codurance.sessionize.sessionizeservice.pairing.Status;
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

  private static final String WEEKLY_CRON_SCHEDULE = "0 30 8 * * MON";
  public static final String SLACKBOT_MATCHLIST_URL = "https://077d0be6e34b.ngrok.io/match-list";
  private static final String EUROPE_LONDON = "Europe/London";
  RestTemplate restTemplate;
  MatchingService matchingService;
  PairingsRepository pairingsRepository;
  UserRepository userRepository;


  public SlackClient(MatchingService matchingService,
                     PairingsRepository pairingsRepository,
                     UserRepository userRepository,
                     RestTemplate restTemplate
  ) {
    this.matchingService = matchingService;
    this.pairingsRepository = pairingsRepository;
    this.userRepository = userRepository;
    this.restTemplate = restTemplate;
  }

  @Scheduled(cron = WEEKLY_CRON_SCHEDULE, zone = EUROPE_LONDON)
  public void pushNewPairings() throws HttpServerErrorException {
    List<SlackPairingRequest> slackPairingRequests = generateSlackPairingHttpRequest();
    restTemplate.postForObject(SLACKBOT_MATCHLIST_URL, slackPairingRequests, Void.class);
  }

  private List<SlackPairingRequest> generateSlackPairingHttpRequest() {
    matchingService.generate();     //generate matches to create pairing by contacting the azure function and persist to db
    List<Pairing> pairings = pairingsRepository.findAllByStatus(Status.PENDING);     // get all pairings with pending status from the db
    return mapAsSlackPairingRequest(pairings);
  }

  private List<SlackPairingRequest> mapAsSlackPairingRequest(List<Pairing> pairings) {
    List<SlackPairingRequest> slackPairingRequests = new ArrayList<>();
    pairings.forEach(pairing ->
      slackPairingRequests
        .add(new SlackPairingRequest(pairing.getLanguage(), findSlackIdsFor(pairing))));
    return slackPairingRequests;
  }

  private List<String> findSlackIdsFor(Pairing pairing) {
    List<String> slackUsersIds = new ArrayList<>();
    pairing.getUsers().forEach(user -> slackUsersIds.add(userRepository.findUserByEmail(user).getSlackUser()));
    return slackUsersIds;
  }
}
