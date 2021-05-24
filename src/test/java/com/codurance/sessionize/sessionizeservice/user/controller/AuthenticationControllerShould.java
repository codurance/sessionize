package com.codurance.sessionize.sessionizeservice.user.controller;

import com.codurance.sessionize.sessionizeservice.infrastructure.security.TokenVerification;
import com.codurance.sessionize.sessionizeservice.user.UserDTO;
import com.codurance.sessionize.sessionizeservice.user.WebUserDTO;
import com.codurance.sessionize.sessionizeservice.user.service.UserService;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.json.webtoken.JsonWebSignature;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
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
  UserService userService = mock(UserService.class);

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
      .withStatus(HttpStatus.UNAUTHORIZED.value())
      ));

    CloseableHttpClient httpClient = HttpClients.createDefault();
    HttpGet request = new HttpGet(BASE_URL + AUTH_URL);
    HttpResponse response = httpClient.execute(request);

    assertEquals(HttpStatus.UNAUTHORIZED.value(), response.getStatusLine().getStatusCode());
  }

  @Test
  public void return_200_with_email_on_successful_login() throws IOException {


    wireMockServer
      .stubFor(
        get(urlEqualTo(AUTH_URL))
          .willReturn(aResponse()
            .withHeader(AUTH_HEADER, "Bearer token")
            .withStatus(HttpStatus.OK.value())
          ));

    CloseableHttpClient httpClient = HttpClients.createDefault();
    HttpGet request = new HttpGet(BASE_URL + AUTH_URL);
    HttpResponse response = httpClient.execute(request);

    assertEquals(HttpStatus.OK.value(), response.getStatusLine().getStatusCode());
  }

  @Test
  public void return_correct_user_on_authentication() throws IOException, GeneralSecurityException {

    AuthenticationController controller = new AuthenticationController(mockTokenVerification, userService);

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

    when(userService.signInOrRegister(ArgumentMatchers.any(WebUserDTO.class))).thenReturn(userDTO);
    ResponseEntity<UserDTO> response = controller.authenticate("token");
    UserDTO user = response.getBody();

    assertThat(userDTO).isEqualToComparingFieldByField(user);
  }

  @Test
  public void return_401_on_incorrect_authentication() throws IOException, GeneralSecurityException {

    AuthenticationController controller = new AuthenticationController(mockTokenVerification, userService);
    when(mockTokenVerification.verifyGoogleIdToken(anyString())).thenReturn(
            null
    );
    ResponseEntity<UserDTO> response = controller.authenticate("token");
    assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
  }

  @Test
  public void return_201_created_if_user_received_from_slack_successfully_created() throws IOException {

    wireMockServer
      .stubFor(
        get(urlEqualTo(SLACK + AUTH_URL))
          .willReturn(aResponse()
            .withStatus(HttpStatus.CREATED.value())
          ));

    CloseableHttpClient httpClient = HttpClients.createDefault();
    HttpGet request = new HttpGet(BASE_URL + SLACK + AUTH_URL);
    HttpResponse response = httpClient.execute(request);

    assertEquals(HttpStatus.CREATED.value(), response.getStatusLine().getStatusCode());
  }


}
