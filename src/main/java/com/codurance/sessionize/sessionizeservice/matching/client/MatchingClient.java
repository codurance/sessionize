package com.codurance.sessionize.sessionizeservice.matching.client;

import com.codurance.sessionize.sessionizeservice.matching.MatchesDTO;
import com.codurance.sessionize.sessionizeservice.preferences.repository.UserLanguagePreferences;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class MatchingClient {

    RestTemplate restTemplate = new RestTemplate();

    public MatchesDTO match(List<UserLanguagePreferences> userLanguagePreferences) {

        HttpEntity<List<UserLanguagePreferences>> request = new HttpEntity<>(userLanguagePreferences);
        String matchingUri = "https://pythonmatcher.azurewebsites.net";

        ResponseEntity<MatchesDTO> response = restTemplate.exchange(matchingUri, HttpMethod.POST, request, MatchesDTO.class);

        return response.getBody();
    }
}
