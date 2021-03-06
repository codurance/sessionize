package com.codurance.sessionize.sessionizeservice.infrastructure.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
//      .requiresChannel()
//      .anyRequest()
//      .requiresSecure()
//      .and()
      .csrf()
      .disable()
      .authorizeRequests()
      .anyRequest()
      .permitAll()
      .and()
      .cors();
  }
}


