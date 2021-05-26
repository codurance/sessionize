package com.codurance.sessionize.sessionizeservice.pairings;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import static com.codurance.sessionize.sessionizeservice.utils.Constants.AUTH_HEADER;
import static com.codurance.sessionize.sessionizeservice.utils.Constants.PAIRINGS_URL;

@RestController
@CrossOrigin(origins = "*")
public class PairingsController {

    private PairingRepository repository;

    public PairingsController(PairingRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value = PAIRINGS_URL, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pairing> getPairings(@RequestHeader(AUTH_HEADER) String authorizationHeader) {

        Pairing pairing = repository.getPairings(authorizationHeader);

        ResponseEntity entity = new ResponseEntity(pairing, HttpStatus.OK);
        return entity;
    }
}
