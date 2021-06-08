package com.codurance.sessionize.sessionizeservice.user;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserShould {

  @Test
  void map_the_web_dto_to_the_user_domain() {
    User user = new User();
    GoogleIdToken.Payload payload = new GoogleIdToken.Payload();
    payload.setEmail("foobar@codurance.com");
    payload.set("family_name", "Foo");
    payload.set("given_name", "Bar");
    payload.set("picture", "https://url");
    WebUserDTO webUserDTO = new WebUserDTO(payload);

    user.map(webUserDTO);

    assertEquals(user.getEmail(), webUserDTO.getEmail());
    assertEquals(user.getFirstName(), webUserDTO.getFirstName());
    assertEquals(user.getLastName(), webUserDTO.getLastName());
    assertEquals(user.getPictureURL(), webUserDTO.getPictureURL());
  }

  @Test
  void map_the_slack_dto_to_the_user_domain() {
    User user = new User();
    SlackUserDTO slackUserDTO = new SlackUserDTO();
    slackUserDTO.setEmail("foobar@foobar.com");
    slackUserDTO.setFirstName("foo");
    slackUserDTO.setLastName("bar");
    slackUserDTO.setSlackUser("123ABC");

    user.map(slackUserDTO);

    assertEquals(user.getEmail(), slackUserDTO.getEmail());
    assertEquals(user.getFirstName(), slackUserDTO.getFirstName());
    assertEquals(user.getLastName(), slackUserDTO.getLastName());
    assertEquals(user.getSlackUser(), slackUserDTO.getSlackUser());
  }

}
