package com.codurance.sessionize.sessionizeservice.pairings.service;

import com.codurance.sessionize.sessionizeservice.pairings.Pairing;
import com.codurance.sessionize.sessionizeservice.pairings.repository.PairingsRepository;
import com.codurance.sessionize.sessionizeservice.user.User;
import com.codurance.sessionize.sessionizeservice.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PairingsServiceImpl implements PairingsService {
    private final PairingsRepository pairingsRepository;
    private final UserRepository userRepository;

    @Autowired
    public PairingsServiceImpl(PairingsRepository pairingsRepository, UserRepository userRepository) {
        this.pairingsRepository = pairingsRepository;
        this.userRepository = userRepository;
    }

    public List<Pairing> getPairings(String email) {
        User loggedInUser = userRepository.findUserByEmail(email);
        List<Pairing> rawPairings = pairingsRepository.findPairingsByUserId(loggedInUser.getId());
        return setPartnerUserId(rawPairings, loggedInUser.getId());
    }

    private List<Pairing> setPartnerUserId(List<Pairing> rawPairings, String loggedInUserId) {
        return rawPairings.stream().map(pairing -> {
            String partnerId = pairing.getUserOneId().equals(loggedInUserId)
                    ? pairing.getUserTwoId()
                    : pairing.getUserOneId();
            pairing.setPartnerUserId(partnerId);
            return pairing;
        }).collect(Collectors.toList());
    }
}
