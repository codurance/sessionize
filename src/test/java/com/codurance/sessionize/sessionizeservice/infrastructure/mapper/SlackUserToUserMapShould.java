package com.codurance.sessionize.sessionizeservice.infrastructure.mapper;

import com.codurance.sessionize.sessionizeservice.user.SlackUserDTO;
import com.codurance.sessionize.sessionizeservice.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SlackUserToUserMapShould {

  private ModelMapper modelMapper;

  @BeforeEach
  public void setup() {
    modelMapper = new ModelMapper();
    modelMapper.addMappings(new SlackUserToUserMap());
  }

  @Test
  void validate_if_slack_user_to_user_mapping_object_is_valid() {

    SlackUserDTO slackUserDTO = prepareSlackUserDTO();
    User user = modelMapper.map(slackUserDTO, User.class);

    assertEquals(user.getSlackUser(), slackUserDTO.getSlackUser());
    assertEquals(user.getEmail(), slackUserDTO.getEmail());
    assertEquals(user.getFirstName(), slackUserDTO.getFirstName());
    assertEquals(user.getLastName(), slackUserDTO.getLastName());
    assertEquals(user.isOptIn(), slackUserDTO.isOptIn());

  }

  private SlackUserDTO prepareSlackUserDTO() {
    SlackUserDTO slackUserDTO = new SlackUserDTO();
    slackUserDTO.setSlackUser("123");
    slackUserDTO.setEmail("foobar@foobar.com");
    slackUserDTO.setFirstName("a");
    slackUserDTO.setLastName("b");
    slackUserDTO.setOptIn(true);
    return slackUserDTO;
  }

}
