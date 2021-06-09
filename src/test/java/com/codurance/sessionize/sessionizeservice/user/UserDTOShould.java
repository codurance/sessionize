package com.codurance.sessionize.sessionizeservice.user;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserDTOShould {

  @Test
  void map_the_user_domain_as_response_dto() {
    User user = new User();
    UserDTO userDTO = new UserDTO();
    userDTO.setPictureURL("url://foobar");
    userDTO.setEmail("foobar@foobar.com");
    userDTO.setName("Testy McTester");
    userDTO.setOptIn(true);

    userDTO.map(user);

    assertEquals(user.getEmail(), userDTO.getEmail());
    assertEquals(user.getName(), userDTO.getName());
    assertEquals(user.getPictureURL(), user.getPictureURL());
    assertEquals(user.isOptIn(), userDTO.isOptIn());
  }

}
