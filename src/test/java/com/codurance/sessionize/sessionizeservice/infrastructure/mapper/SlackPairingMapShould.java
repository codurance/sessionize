package com.codurance.sessionize.sessionizeservice.infrastructure.mapper;

import com.codurance.sessionize.sessionizeservice.pairings.Pairing;
import com.codurance.sessionize.sessionizeservice.pairings.PairingDTO;
import com.codurance.sessionize.sessionizeservice.preferences.Language;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SlackPairingMapShould {

  private ModelMapper modelMapper;

  @BeforeEach
  public void setup() {
    modelMapper = new ModelMapper();
    modelMapper.addMappings(new SlackPairingMap());
  }

  @Test
  void validate_if_pairing_mapping_object_is_valid() {

    Pairing pairing = preparePairing();
    PairingDTO pairingDTO = modelMapper.map(pairing, PairingDTO.class);

    modelMapper.validate();

    assertEquals(pairing.getUsers(), pairingDTO.getUserEmails());
    assertEquals(pairing.getLanguage(), pairingDTO.getLanguage());
  }

  private Pairing preparePairing() {

    Pairing pairing = new Pairing();
    pairing.setUsers(Arrays.asList("foobar@baz.com", "bazfoo@bar"));
    pairing.setLanguage(new Language());
    return pairing;
  }

}
