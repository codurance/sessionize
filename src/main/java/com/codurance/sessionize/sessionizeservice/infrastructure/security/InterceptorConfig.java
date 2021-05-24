package com.codurance.sessionize.sessionizeservice.infrastructure.security;
import com.codurance.sessionize.sessionizeservice.infrastructure.security.TokenVerification;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


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
            .excludePathPatterns("/auth", "/isNewUser");
  }
}


