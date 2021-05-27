package com.codurance.sessionize.sessionizeservice.infrastructure.mapper;

import com.codurance.sessionize.sessionizeservice.preferences.LanguagesPreferences;
import com.codurance.sessionize.sessionizeservice.preferences.LanguagesPreferencesDTO;
import org.modelmapper.PropertyMap;

public class LanguageMap extends PropertyMap<LanguagesPreferencesDTO, LanguagesPreferences> {


  @Override
  protected void configure() {
    map(source.getPrimaryLanguage(), destination.getPrimary());
    map(source.getSecondaryLanguage(), destination.getSecondary());
    map(source.getTertiaryLanguage(), destination.getTertiary());
  }
}
