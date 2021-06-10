package com.codurance.sessionize.sessionizeservice.matching.client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AzureLanguagePreferencesRequest {

  String primary;
  String secondary;
  String tertiary;

}
