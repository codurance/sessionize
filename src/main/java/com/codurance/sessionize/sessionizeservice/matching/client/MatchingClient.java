package com.codurance.sessionize.sessionizeservice.matching.client;

import com.codurance.sessionize.sessionizeservice.preferences.UserLanguagePreferences;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class MatchingClient {

    RestTemplate restTemplate = new RestTemplate();
    private static final String MATCHING_URI = "https://pythonmatchertestfn.azurewebsites.net/api/SessionizeMatching?";

    public List<MatchResponse> match(List<UserLanguagePreferences> userLanguagePreferences) throws HttpServerErrorException {

        List<UserLanguagePreferencesRequest> preferencesRequest = preparePreferencesRequest(userLanguagePreferences);
        MatchRequest matchRequest = new MatchRequest(preferencesRequest);
        HttpEntity<MatchRequest> request = new HttpEntity<>(matchRequest);

        ResponseEntity<List<MatchResponse>> response = restTemplate.exchange(MATCHING_URI, HttpMethod.POST, request, new ParameterizedTypeReference<>() {});

        return response.getBody();
    }

    private List<UserLanguagePreferencesRequest> preparePreferencesRequest(List<UserLanguagePreferences> userLanguagePreferences) {
        List<UserLanguagePreferencesRequest> preferencesRequest = new ArrayList<>();

        userLanguagePreferences.forEach(preference ->
              preferencesRequest.add(new UserLanguagePreferencesRequest(preference))
        );
        return preferencesRequest;
    }
}
