package com.codurance.sessionize.sessionizeservice.preferences;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LanguagesPreferences {
  Language primary;
  Language secondary;
  Language tertiary;
  
  @Override
  public boolean equals(Object obj) {
  
      if (obj == this) return true;
      if (!(obj instanceof LanguagesPreferences)) return false;
      
      LanguagesPreferences languagesPreferences = (LanguagesPreferences) obj;
      return Objects.equals(primary, languagesPreferences.primary) &&
              Objects.equals(secondary, languagesPreferences.secondary) &&
              Objects.equals(tertiary, languagesPreferences.tertiary);
  }
  
  @Override
  public int hashCode() {
      return Objects.hash(primary, secondary, tertiary);
  }
}
