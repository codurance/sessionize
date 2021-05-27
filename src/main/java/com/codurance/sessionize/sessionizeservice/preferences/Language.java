package com.codurance.sessionize.sessionizeservice.preferences;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Language {

  String value;
  String displayName;

  @Override
  public String toString() {
    return "Language{" +
      "value='" + value + '\'' +
      ", displayName='" + displayName + '\'' +
      '}';
  }
}
