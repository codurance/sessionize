package com.codurance.sessionize.sessionizeservice.pairings;

import org.springframework.stereotype.Component;

@Component
public interface PairingRepository {

  public Pairing getPairings(String tokenId);

}
