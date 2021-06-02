package com.codurance.sessionize.sessionizeservice.pairings.service;

import com.codurance.sessionize.sessionizeservice.pairings.Pairing;
import com.codurance.sessionize.sessionizeservice.pairings.Status;
import com.codurance.sessionize.sessionizeservice.pairings.repository.PairingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PairingsServiceImpl implements PairingsService {
    private final PairingsRepository pairingsRepository;

    @Autowired
    public PairingsServiceImpl(PairingsRepository pairingsRepository) {
        this.pairingsRepository = pairingsRepository;
    }

    public List<Pairing> getPairings(String email) {
        List<Pairing> rawPairings = pairingsRepository.findPairingsByEmail(email);
        return setPartnerUserId(rawPairings, email);
    }

    @Override
    public List<Pairing> getPairingsBy(Status status, String email) {
        return null;
    }

    private List<Pairing> setPartnerUserId(List<Pairing> rawPairings, String loggedInUserId) {
        return rawPairings.stream().map(pairing -> {
            String partnerId = pairing.getUsers().stream().
                    filter(userId -> !userId.equals(loggedInUserId)).
                    findFirst().orElseThrow();
            pairing.setPartnerUserId(partnerId);
            return pairing;
        }).collect(Collectors.toList());
    }
}
