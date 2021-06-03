package com.codurance.sessionize.sessionizeservice.pairing.service;

import com.codurance.sessionize.sessionizeservice.pairing.Pairing;
import com.codurance.sessionize.sessionizeservice.pairing.PairingDTO;
import com.codurance.sessionize.sessionizeservice.pairing.Status;
import com.codurance.sessionize.sessionizeservice.pairing.repository.PairingsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PairingsServiceImpl implements PairingsService {

    private final PairingsRepository pairingsRepository;
    private final ModelMapper mapper;

    @Autowired
    public PairingsServiceImpl(PairingsRepository pairingsRepository, ModelMapper mapper) {
        this.pairingsRepository = pairingsRepository;
        this.mapper = mapper;
    }

    public List<Pairing> getPairings(String email) {
        List<Pairing> rawPairings = pairingsRepository.findPairingByUsersContaining(email);
        return setPartnerUserId(rawPairings, email);
    }

    @Override
    public List<PairingDTO> getPairingsBy(Status status, String email) {
        List<Pairing> rawPairings = pairingsRepository.findPairingsByUsersContainsAndStatus(email, status);
        List<Pairing> withPartnerUserId = setPartnerUserId(rawPairings, email);
        return mapToDTO(withPartnerUserId);

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

    private List<PairingDTO> mapToDTO(List<Pairing> withPartnerUserId) {
        List<PairingDTO> mappedPairings = new ArrayList<>();
        //TODO: change this to modelmapper
        for (Pairing p: withPartnerUserId) {
            PairingDTO pairingDTO = new PairingDTO();
            pairingDTO.setLanguage(p.getLanguage());
            pairingDTO.setUserEmails(p.getUsers());
            mappedPairings.add(pairingDTO);
        }
        return mappedPairings;
    }
}
