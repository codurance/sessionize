package com.codurance.sessionize.sessionizeservice.user;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceImplShould {

  CustomUserRepository customUserRepository = mock(CustomUserRepository.class);
  UserServiceImpl userService = new UserServiceImpl(customUserRepository);

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
      "Foo",
      "Bar",
      "http://url"
    ));
    UserDTO expectedUser = userService.signInOrRegister(receivedUser);


    assertThat(expectedUser).isEqualToComparingFieldByField(receivedUser);


  }

}
