package com.codurance.sessionize.sessionizeservice.matching.client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class MatchRequest {

  private List<UserLanguagePreferencesRequest> matchRequest;

}
