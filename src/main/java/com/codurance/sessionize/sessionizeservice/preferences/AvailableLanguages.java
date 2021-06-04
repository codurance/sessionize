package com.codurance.sessionize.sessionizeservice.preferences;

import java.util.Arrays;
import java.util.List;

public class AvailableLanguages {

  public static List<Language> get() {
    return Arrays.asList(
      new Language("JAVA", "Java"),
      new Language("CSHARP", "C#"),
      new Language("GOLANG", "Go"),
      new Language("CPP", "C++")
    );
  }

}
