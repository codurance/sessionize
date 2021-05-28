package com.codurance.sessionize.sessionizeservice.preferences;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LanguagesPreferencesDTO {

  private Language primaryLanguage;
  private Language secondaryLanguage;
  private Language tertiaryLanguage;

}
