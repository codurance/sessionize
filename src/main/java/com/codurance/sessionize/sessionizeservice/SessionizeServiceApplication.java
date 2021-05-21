package com.codurance.sessionize.sessionizeservice;

import com.codurance.sessionize.sessionizeservice.authentication.TokenVerification;
import com.codurance.sessionize.sessionizeservice.slack.SlackRestClient;
import com.codurance.sessionize.sessionizeservice.user.repository.CustomUserRepository;
import com.codurance.sessionize.sessionizeservice.user.repository.CustomUserRepositoryImpl;
import com.codurance.sessionize.sessionizeservice.user.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SessionizeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SessionizeServiceApplication.class, args);
	}

	@Bean
	public SlackRestClient statusRestClient() {
		return new SlackRestClient();
	}

	@Bean
	public TokenVerification tokenVerification() {return new TokenVerification(); }

	@Bean
	public CustomUserRepository customUserRepository(UserRepository userRepository) {
		return new CustomUserRepositoryImpl(userRepository);
	}

}
