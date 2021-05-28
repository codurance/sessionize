package com.codurance.sessionize.sessionizeservice.pairings;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PairingRepository {

  public List<Pairing> getPairings(String tokenId);

}
