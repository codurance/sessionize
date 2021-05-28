package com.codurance.sessionize.sessionizeservice.preferences;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
public class Language {

  String value;
  String displayName;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Language)) return false;
    Language language = (Language) o;
    return getValue().equals(language.getValue()) && getDisplayName().equals(language.getDisplayName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getValue(), getDisplayName());
  }
}
