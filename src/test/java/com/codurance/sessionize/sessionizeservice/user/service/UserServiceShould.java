package com.codurance.sessionize.sessionizeservice.user.service;

import com.codurance.sessionize.sessionizeservice.user.SlackUserDTO;
import com.codurance.sessionize.sessionizeservice.user.User;
import com.codurance.sessionize.sessionizeservice.user.UserDTO;
import com.codurance.sessionize.sessionizeservice.user.WebUserDTO;
import com.codurance.sessionize.sessionizeservice.user.repository.CustomUserRepository;
import com.codurance.sessionize.sessionizeservice.user.repository.UserRepository;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserServiceShould {

  CustomUserRepository customUserRepository = mock(CustomUserRepository.class);
  UserRepository userRepository = mock(UserRepository.class);
  UserServiceImpl userService = new UserServiceImpl(customUserRepository, userRepository);

  @Test
  public void return_the_mapped_persisted_web_authenticated_user() {

    GoogleIdToken.Payload payload = new GoogleIdToken.Payload();
    payload.setEmail("foobar@codurance.com");
    payload.set("family_name", "Foo");
    payload.set("given_name", "Bar");
    payload.set("picture", "http://url");
    WebUserDTO receivedUser = new WebUserDTO(payload);

    when(customUserRepository.findByEmailOrCreate(any(User.class))).thenReturn(new User(
      "foobar@codurance.com",
      "http://url",
      "Bar",
      "Foo"
    ));
    UserDTO expectedUser = userService.webSignInOrRegister(receivedUser);

    assertThat(expectedUser).isEqualToComparingFieldByField(receivedUser);
  }

  @Test
  void return_true_if_no_user_found_in_the_db_associated_with_the_provided_email() {

    String stubEmail = "foobar@codurance.com";
    when(userRepository.existsByEmail(stubEmail)).thenReturn(false);
    boolean actual = userService.isNewUser("foobar@codurance.com");
    assertTrue(actual);
  }

  @Test
  void map_DTO_and_save_user_to_db() {
    userService.slackRegister(new SlackUserDTO());
    verify(userRepository, times(1)).save(any(User.class));
  }

  @Test
  void updates_slack_id_for_existing_user() {

    String stubEmail = "foobar@codurance.com";
    SlackUserDTO stubSlackUserDTO = new SlackUserDTO();
    stubSlackUserDTO.setEmail(stubEmail);
    stubSlackUserDTO.setSlackId("123");
    User stubUser = new User();

    when(userRepository.findUserByEmail(stubEmail)).thenReturn(stubUser);
    userService.updateSlackIdFor(stubSlackUserDTO);

    verify(userRepository, times(1)).save(any(User.class));
  }


}
