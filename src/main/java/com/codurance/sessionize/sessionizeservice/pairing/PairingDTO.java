package com.codurance.sessionize.sessionizeservice.pairing;

import com.codurance.sessionize.sessionizeservice.preferences.Language;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PairingDTO {

  List<String> userEmails;
  Language language;

}
