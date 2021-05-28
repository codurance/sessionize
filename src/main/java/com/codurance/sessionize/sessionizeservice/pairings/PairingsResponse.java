package com.codurance.sessionize.sessionizeservice.pairings;

import com.codurance.sessionize.sessionizeservice.preferences.Language;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PairingsResponse {
    String user1;
    String user2;
    Language language;
    Status status;
}
