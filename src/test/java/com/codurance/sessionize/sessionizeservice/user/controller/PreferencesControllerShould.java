package com.codurance.sessionize.sessionizeservice.user.controller;

import com.codurance.sessionize.sessionizeservice.user.service.UserService;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.HttpStatus;

import java.io.IOException;

import static com.codurance.sessionize.sessionizeservice.infrastructure.constants.HttpConstants.*;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
@AutoConfigureWireMock(port = 8080)

public class PreferencesControllerShould {

  private static final String BASE_URL = "http://localhost:8080";

  WireMockServer wireMockServer = new WireMockServer(options().port(8080));
  UserService mockUserService = mock(UserService.class);

  @BeforeEach
  public void setup() {
    wireMockServer.start();
  }

  @AfterEach
  public void teardown() {
    wireMockServer.stop();
  }

  @Test
  void call_the_opt_out_endpoint_without_token_and_with_a_email_as_request_param() throws IOException {
    wireMockServer
      .stubFor(
        put(urlEqualTo(SLACK + OPT_OUT + "?email=andras.dako@gmail.com"))
          .willReturn(aResponse()
            .withStatus(HttpStatus.OK.value())
          ));

    CloseableHttpClient httpClient = HttpClients.createDefault();
    HttpPut request = new HttpPut(BASE_URL + SLACK + OPT_OUT + "?email=andras.dako@gmail.com");
    HttpResponse response = httpClient.execute(request);

    assertEquals(HttpStatus.OK.value(), response.getStatusLine().getStatusCode());
  }

}
