package com.codurance.sessionize.sessionizeservice.authentication;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;

import java.io.IOException;
import static com.codurance.sessionize.sessionizeservice.utils.Constants.AUTH_HEADER;
import static com.codurance.sessionize.sessionizeservice.utils.Constants.AUTH_URL;
import static com.codurance.sessionize.sessionizeservice.utils.Constants.OK;
import static com.codurance.sessionize.sessionizeservice.utils.Constants.UNAUTHORIZED;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.junit.jupiter.api.Assertions.assertEquals;

@AutoConfigureWireMock(port = 8080)
public class AuthenticationControllerShould {

  private static final String BASE_URL = "http://localhost:8080";


  WireMockServer wireMockServer = new WireMockServer(options().port(8080));

  @BeforeEach
  public void setup() {
    wireMockServer.start();

  }

  @AfterEach
  public void teardown() {
    wireMockServer.stop();
  }


  @Test
  public void return_401_unauthorized_if_user_is_not_permitted_to_login() throws IOException {

    wireMockServer
      .stubFor(
        get(urlEqualTo(AUTH_URL))
      .willReturn(aResponse()
      .withHeader(AUTH_HEADER, "Bearer token")
      .withStatus(UNAUTHORIZED)
      ));

    CloseableHttpClient httpClient = HttpClients.createDefault();
    HttpGet request = new HttpGet(BASE_URL + AUTH_URL);
    HttpResponse response = httpClient.execute(request);

    assertEquals(UNAUTHORIZED, response.getStatusLine().getStatusCode());
  }

  @Test
  public void return_200_with_email_on_successful_login() throws IOException {


    wireMockServer
      .stubFor(
        get(urlEqualTo(AUTH_URL))
          .willReturn(aResponse()
            .withHeader(AUTH_HEADER, "Bearer token")
            .withStatus(OK)
          ));

    CloseableHttpClient httpClient = HttpClients.createDefault();
    HttpGet request = new HttpGet(BASE_URL + AUTH_URL);
    HttpResponse response = httpClient.execute(request);

    assertEquals(OK, response.getStatusLine().getStatusCode());
  }



}
