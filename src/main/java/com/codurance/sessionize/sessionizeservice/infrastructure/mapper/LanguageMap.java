package com.codurance.sessionize.sessionizeservice.infrastructure.mapper;

import com.codurance.sessionize.sessionizeservice.preferences.Languages;
import com.codurance.sessionize.sessionizeservice.preferences.LanguagesDTO;
import org.modelmapper.PropertyMap;

public class LanguageMap extends PropertyMap<LanguagesDTO, Languages> {


  @Override
  protected void configure() {
    map(source.getPrimaryLanguage(), destination.getPrimary().getName());
    map(source.getSecondaryLanguage(), destination.getSecondary().getName());
    map(source.getTertiaryLanguage(), destination.getTertiary().getName());
  }
}
