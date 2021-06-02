package com.codurance.sessionize.sessionizeservice.infrastructure.mapper;

import com.codurance.sessionize.sessionizeservice.pairings.Pairing;
import com.codurance.sessionize.sessionizeservice.pairings.PairingDTO;
import org.modelmapper.PropertyMap;

public class SlackPairingMap extends PropertyMap<Pairing, PairingDTO> {
  @Override
  protected void configure() {
    map(source.getUsers(), destination.getUserEmails());
    map(source.getLanguage(), destination.getLanguage());
  }
}
