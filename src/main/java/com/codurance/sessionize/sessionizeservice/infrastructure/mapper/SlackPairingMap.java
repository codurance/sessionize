package com.codurance.sessionize.sessionizeservice.infrastructure.mapper;

import com.codurance.sessionize.sessionizeservice.pairing.Pairing;
import com.codurance.sessionize.sessionizeservice.pairing.PairingDTO;
import org.modelmapper.PropertyMap;

public class SlackPairingMap extends PropertyMap<Pairing, PairingDTO> {
  @Override
  protected void configure() {
    map(source.getUsers(), destination.getUserEmails());
    map(source.getLanguage(), destination.getLanguage());
  }
}
