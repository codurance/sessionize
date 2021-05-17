package com.codurance.sessionize.sessionizeservice.config.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import static org.springframework.http.HttpMethod.POST;

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
      .permitAll();
  }
}


