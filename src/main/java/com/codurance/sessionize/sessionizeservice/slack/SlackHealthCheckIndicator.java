package com.codurance.sessionize.sessionizeservice.slack;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class SlackHealthCheckIndicator implements HealthIndicator {


  SlackRestClient statusRestClient;

  public SlackHealthCheckIndicator(SlackRestClient statusRestClient) {
    this.statusRestClient = statusRestClient;
  }

  @Override
  public Health health() {

    Status slackStatus = statusRestClient.get();
    Health.Builder localStatus = Health.up();

    if (slackStatus.isDown()) {
      localStatus = localStatus.down();
    }
    return localStatus.build();
  }
}
