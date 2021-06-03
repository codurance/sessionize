package com.codurance.sessionize.sessionizeservice.pairing.client;

import com.codurance.sessionize.sessionizeservice.matching.service.MatchingService;
import com.codurance.sessionize.sessionizeservice.pairing.Pairing;
import com.codurance.sessionize.sessionizeservice.pairing.Status;
import com.codurance.sessionize.sessionizeservice.pairing.repository.PairingsRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class SlackClient {

  RestTemplate restTemplate = new RestTemplate();
  MatchingService matchingService;
  PairingsRepository pairingsRepository;


  public SlackClient(MatchingService matchingService, PairingsRepository pairingsRepository) {
    this.matchingService = matchingService;
    this.pairingsRepository = pairingsRepository;
  }

  @Scheduled(cron = "0 58 16 * * THU", zone = "Europe/London")
  public void pushPairingsToSlack() {
  //TODO: next up - cron job runs, check if we can send the request to slack properly then complete
    System.out.println("cron ran");

    //generate matches to create pairing by contacting the azure function and persist to db
    matchingService.generate();

    //get all pairings with pending status from the db
    List<Pairing> pairings = pairingsRepository.findAllByStatus(Status.PENDING);

    //map pairing to a slackpairingrequest
    List<SlackPairingRequest> slackPairingRequests = mapAsSlackPairingRequest(pairings);

    //call endpoint
    restTemplate.postForObject("https://3278ef3ceb1a.ngrok.io/match-list", slackPairingRequests, Void.class);
  }

  public List<SlackPairingRequest> mapAsSlackPairingRequest(List<Pairing> pairings) {

    List<SlackPairingRequest> slackPairingRequests = new ArrayList<>();

    pairings.forEach(pairing ->
      slackPairingRequests.add(new SlackPairingRequest(pairing.getLanguage(), pairing.getUsers())));

   return slackPairingRequests;

  }

}
