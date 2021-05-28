package com.codurance.sessionize.sessionizeservice.preferences;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LanguagesPreferences {
  Language primary;
  Language secondary;
  Language tertiary;
}
