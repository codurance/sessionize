package com.codurance.sessionize.sessionizeservice.user.controller;

import com.codurance.sessionize.sessionizeservice.infrastructure.security.TokenVerification;
import com.codurance.sessionize.sessionizeservice.user.SlackUserDTO;
import com.codurance.sessionize.sessionizeservice.user.UserDTO;
import com.codurance.sessionize.sessionizeservice.user.WebUserDTO;
import com.codurance.sessionize.sessionizeservice.user.service.UserService;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.json.webtoken.JsonWebSignature;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.security.GeneralSecurityException;

import static com.codurance.sessionize.sessionizeservice.infrastructure.constants.HttpConstants.*;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@AutoConfigureWireMock(port = 8080)
public class AuthenticationControllerShould {

  private static final String BASE_URL = "http://localhost:8080";

  WireMockServer wireMockServer = new WireMockServer(options().port(8080));
  TokenVerification mockTokenVerification = mock(TokenVerification.class);
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
  public void auth_endpoint_can_return_401() throws IOException {

    wireMockServer
      .stubFor(
        get(urlEqualTo(AUTH))
      .willReturn(aResponse()
      .withHeader(AUTH_HEADER, "Bearer token")
      .withStatus(HttpStatus.UNAUTHORIZED.value())
      ));

    CloseableHttpClient httpClient = HttpClients.createDefault();
    HttpGet request = new HttpGet(BASE_URL + AUTH);
    HttpResponse response = httpClient.execute(request);

    assertEquals(HttpStatus.UNAUTHORIZED.value(), response.getStatusLine().getStatusCode());
  }

  @Test
  public void auth_endpoint_can_return_200() throws IOException {


    wireMockServer
      .stubFor(
        get(urlEqualTo(AUTH))
          .willReturn(aResponse()
            .withHeader(AUTH_HEADER, "Bearer token")
            .withStatus(HttpStatus.OK.value())
          ));

    CloseableHttpClient httpClient = HttpClients.createDefault();
    HttpGet request = new HttpGet(BASE_URL + AUTH);
    HttpResponse response = httpClient.execute(request);

    assertEquals(HttpStatus.OK.value(), response.getStatusLine().getStatusCode());
  }

  @Test
  public void slack_auth_endpoint_can_return_201() throws IOException {

    wireMockServer
      .stubFor(
        post(urlEqualTo(SLACK + AUTH))
          .willReturn(aResponse()
            .withStatus(HttpStatus.CREATED.value())
          ));

    CloseableHttpClient httpClient = HttpClients.createDefault();
    HttpPost request = new HttpPost(BASE_URL + SLACK + AUTH);
    HttpResponse response = httpClient.execute(request);

    assertEquals(HttpStatus.CREATED.value(), response.getStatusLine().getStatusCode());
  }

  @Test
  public void return_correct_user_on_authentication() throws IOException, GeneralSecurityException {

    AuthenticationController controller = new AuthenticationController(mockTokenVerification, mockUserService);

    JsonWebSignature.Header header = new JsonWebSignature.Header();
    GoogleIdToken.Payload payload = new GoogleIdToken.Payload();
    payload.setEmail("foobar@codurance.com");
    payload.set("family_name", "Foo");
    payload.set("given_name", "Bar");
    payload.set("picture", "https://url");
    byte[] signatureBytes = new byte[0];
    byte[] signedContentBytes = new byte[0];
    WebUserDTO webUserDTO = new WebUserDTO(payload);

    when(mockTokenVerification.verifyGoogleIdToken(anyString())).thenReturn(
      new GoogleIdToken(
        header,
        payload,
        signatureBytes,
        signedContentBytes
      ));

    UserDTO userDTO = new UserDTO();
    userDTO.setEmail("foobar@codurance.com");
    userDTO.setFirstName("Bar");
    userDTO.setPictureURL("http://url");

    when(mockUserService.webSignInOrRegister(ArgumentMatchers.any(WebUserDTO.class))).thenReturn(userDTO);
    ResponseEntity<UserDTO> response = controller.authenticate("token");
    UserDTO user = response.getBody();

    assertThat(userDTO).isEqualToComparingFieldByField(user);
  }

  @Test
  public void return_401_on_incorrect_authentication() throws IOException, GeneralSecurityException {

    AuthenticationController controller = new AuthenticationController(mockTokenVerification, mockUserService);
    when(mockTokenVerification.verifyGoogleIdToken(anyString())).thenReturn(
            null
    );
    ResponseEntity<UserDTO> response = controller.authenticate("token");
    assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
  }

  @Test
  public void return_204_and_and_calls_to_update_slack_id_if_user_exists() throws IOException, GeneralSecurityException {

    AuthenticationController controller = new AuthenticationController(mockTokenVerification, mockUserService);
    SlackUserDTO slackUserDTO = new SlackUserDTO();
    slackUserDTO.setEmail("foobar@foobar.com");
    when(mockUserService.isNewUser(slackUserDTO.getEmail())).thenReturn(true);
    ResponseEntity<UserDTO> response = controller.authenticate(slackUserDTO);
    assertEquals(HttpStatus.CREATED, response.getStatusCode());
  }

  @Test
  public void return_201_and_and_calls_to_save_slack_user_if_doesnt_exist() throws IOException, GeneralSecurityException {

    AuthenticationController controller = new AuthenticationController(mockTokenVerification, mockUserService);
    SlackUserDTO slackUserDTO = new SlackUserDTO();
    slackUserDTO.setEmail("foobar@foobar.com");
    when(mockUserService.isNewUser(slackUserDTO.getEmail())).thenReturn(false);
    ResponseEntity<UserDTO> response = controller.authenticate(slackUserDTO);
    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
  }
}
