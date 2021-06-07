package com.codurance.sessionize.sessionizeservice.pairing.client;


import com.codurance.sessionize.sessionizeservice.pairing.Status;
import com.codurance.sessionize.sessionizeservice.preferences.Language;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class SlackPairingRequest {

  Language language;
  List<String> users;
  Status status;

}
