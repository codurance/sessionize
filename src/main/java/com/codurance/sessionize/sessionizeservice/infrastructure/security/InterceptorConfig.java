package com.codurance.sessionize.sessionizeservice.infrastructure.security;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static com.codurance.sessionize.sessionizeservice.infrastructure.constants.HttpConstants.*;


@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

  TokenVerification tokenVerification;

  public InterceptorConfig(TokenVerification tokenVerification) {
    this.tokenVerification = tokenVerification;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(tokenVerification)
            .addPathPatterns("/**")
            .excludePathPatterns(
              AUTH,
              SLACK + AUTH,
              SLACK + OPT_OUT,
              SLACK + PREFERENCES + LANGUAGES,
              SLACK + LANGUAGES
            );
  }
}


