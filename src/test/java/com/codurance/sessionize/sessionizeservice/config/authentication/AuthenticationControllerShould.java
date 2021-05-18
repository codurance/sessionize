package com.codurance.sessionize.sessionizeservice.config.authentication;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.stubbing.StubMapping;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;


import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.junit.jupiter.api.Assertions.assertEquals;

@AutoConfigureWireMock(port = 8080)
public class AuthenticationControllerShould {



  WireMockServer wireMockServer = new WireMockServer(options().port(8080));

  @BeforeEach
  public void setup() {
    wireMockServer.start();

  }

  @AfterEach
  public void teardown() {
    wireMockServer.stop();
  }

  public MappingBuilder POST(WireMockServer wireMockServer, String endpoint) {
    return (MappingBuilder) wireMockServer
      .stubFor(
        post(urlEqualTo("/auth")));
  }


  @Test
  public void return_401_unauthorized_if_user_is_not_permitted_to_login() throws IOException {

    ObjectMapper om = new ObjectMapper();
    JsonNode body = om.readTree("{\"email\":\"\"}");

    wireMockServer
      .stubFor(
        post(urlEqualTo("/auth"))
      .willReturn(aResponse()
      .withHeader("Authorization", "Bearer token")
      .withStatus(401)
      .withJsonBody(body)
      ));

    CloseableHttpClient httpClient = HttpClients.createDefault();
    HttpPost request = new HttpPost(("http://localhost:8080/auth"));
    HttpResponse response = httpClient.execute(request);

    assertEquals(401, response.getStatusLine().getStatusCode());
  }

  @Test
  public void return_200_with_email_on_successful_login() throws IOException {

    ObjectMapper om = new ObjectMapper();
    JsonNode body = om.readTree("{\"email\":\"foobar@gmail.com\"}");

    wireMockServer
      .stubFor(
        post(urlEqualTo("/auth"))
          .willReturn(aResponse()
            .withHeader("Authorization", "Bearer token")
            .withStatus(200)
            .withJsonBody(body)
          ));

    CloseableHttpClient httpClient = HttpClients.createDefault();
    HttpPost request = new HttpPost(("http://localhost:8080/auth"));
    HttpResponse response = httpClient.execute(request);

    assertEquals(200, response.getStatusLine().getStatusCode());
  }



}
