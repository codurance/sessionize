package com.codurance.sessionize.sessionizeservice.pairings;

import java.util.ArrayList;
import java.util.List;

public class PairingRepositoryImpl implements PairingRepository {
    public List<Pairing> getPairings(String tokenId){
        List<Pairing> pairings = new ArrayList<>();
        pairings.add(new Pairing());
        pairings.add(new Pairing());
        return pairings;
    }
}
