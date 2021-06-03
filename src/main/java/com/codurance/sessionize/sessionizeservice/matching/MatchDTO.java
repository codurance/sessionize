package com.codurance.sessionize.sessionizeservice.matching;
import com.codurance.sessionize.sessionizeservice.preferences.Language;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MatchDTO {

  List<String> emails;
  Language language;

}
