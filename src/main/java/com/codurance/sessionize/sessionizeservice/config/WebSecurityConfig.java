package com.codurance.sessionize.sessionizeservice.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      /*
      comment this out to remove TLS
       */
      .requiresChannel()
      .anyRequest()
      .requiresSecure()
      .and()

      .authorizeRequests()
      .anyRequest()
      .permitAll();
  }
}


