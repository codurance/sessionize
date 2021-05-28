package com.codurance.sessionize.sessionizeservice.infrastructure.mapper;

import com.codurance.sessionize.sessionizeservice.user.SlackUserDTO;
import com.codurance.sessionize.sessionizeservice.user.User;
import org.modelmapper.PropertyMap;

public class SlackUserToUserMap extends PropertyMap<SlackUserDTO, User> {

  @Override
  protected void configure() {
    map(source.getEmail(), destination.getEmail());
    map(source.getSlackUser(), destination.getSlackUser());
    map(source.getFirstName(), destination.getFirstName());
    map(source.getLastName(), destination.getLastName());
    map(source.isOptIn(),destination.isOptIn());
  }
}
