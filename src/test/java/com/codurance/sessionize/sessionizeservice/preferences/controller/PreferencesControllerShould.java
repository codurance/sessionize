package com.codurance.sessionize.sessionizeservice.preferences.controller;

import com.codurance.sessionize.sessionizeservice.preferences.Language;
import com.codurance.sessionize.sessionizeservice.preferences.LanguagesPreferencesDTO;
import com.codurance.sessionize.sessionizeservice.preferences.service.PreferencesService;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static com.codurance.sessionize.sessionizeservice.infrastructure.constants.HttpConstants.*;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@AutoConfigureWireMock(port = 8080)
public class PreferencesControllerShould {

  private static final String BASE_URL = "http://localhost:8080";

  PreferencesController preferencesController;
  WireMockServer wireMockServer = new WireMockServer(options().port(8080));
  PreferencesService mockPreferencesService = mock(PreferencesService.class);
  List<Language> languages;

  @BeforeEach
  public void setup() {
    preferencesController = new PreferencesController(mockPreferencesService);
    wireMockServer.start();

    languages = Arrays.asList(
      new Language("JAVA", "Java"),
      new Language("CSHARP", "C#"),
      new Language("GOLANG", "Go"),
      new Language("CPP", "C++")
    );

  }

  @AfterEach
  public void teardown() {
    wireMockServer.stop();
  }

  @Test
  void call_the_opt_out_endpoint_without_token_and_with_a_email_as_request_param() throws IOException {
    wireMockServer
      .stubFor(
        put(urlEqualTo(SLACK + AVAILABILITY + "?email=andras.dako@gmail.com"))
          .willReturn(aResponse()
            .withStatus(HttpStatus.OK.value())
          ));

    CloseableHttpClient httpClient = HttpClients.createDefault();
    HttpPut request = new HttpPut(BASE_URL + SLACK + AVAILABILITY + "?email=andras.dako@gmail.com");
    HttpResponse response = httpClient.execute(request);

    assertEquals(HttpStatus.OK.value(), response.getStatusLine().getStatusCode());
  }

  @Test
  void call_all_languages_endpoint_for_slack() throws IOException {


    wireMockServer
      .stubFor(
        get(urlEqualTo(SLACK + PREFERENCES))
          .willReturn(aResponse()
            .withStatus(HttpStatus.OK.value())
            .withBody(languages.toString()))
      );

    CloseableHttpClient httpClient = HttpClients.createDefault();
    HttpGet request = new HttpGet(BASE_URL + SLACK + PREFERENCES);
    HttpResponse response = httpClient.execute(request);

    assertEquals(languages.toString(), EntityUtils.toString(response.getEntity()));
  }

  @Test
  void return_200_if_user_availability_changed_successfully() {
    String stubEmail = "foobar@gmail.com";
    when(mockPreferencesService.changeAvailability(stubEmail)).thenReturn(true);
    ResponseEntity entity = preferencesController.changeAvailability(stubEmail);
    assertEquals(HttpStatus.OK, entity.getStatusCode());
  }

  @Test
  void save_the_language_preferences_for_a_user_through_slack() {
    LanguagesPreferencesDTO languagesPreferencesDTO = new LanguagesPreferencesDTO();
    String slackUser = "slackUser";
    preferencesController.languagePreferencesSlack(slackUser, languagesPreferencesDTO);
    Mockito.verify(mockPreferencesService, times(1)).setLanguagesForSlack(languagesPreferencesDTO, slackUser);
  }

}
