package com.codurance.sessionize.sessionizeservice.config.slack;

import org.springframework.web.client.RestTemplate;

public class SlackRestClient implements StatusRestClient {

  private static final String URL = "https://status.slack.com/api/v2.0.0/current";
  RestTemplate restTemplate = new RestTemplate();

  public Status get() {
    return restTemplate.getForObject(URL, Status.class);
  }
}
