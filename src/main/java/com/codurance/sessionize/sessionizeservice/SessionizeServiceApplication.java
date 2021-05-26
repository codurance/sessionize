package com.codurance.sessionize.sessionizeservice;

import com.codurance.sessionize.sessionizeservice.infrastructure.security.TokenVerification;
import com.codurance.sessionize.sessionizeservice.infrastructure.health.SlackRestClient;
import com.codurance.sessionize.sessionizeservice.preferences.repository.PreferencesRepository;
import com.codurance.sessionize.sessionizeservice.preferences.repository.PreferencesRepositoryImpl;
import com.codurance.sessionize.sessionizeservice.preferences.service.PreferencesService;
import com.codurance.sessionize.sessionizeservice.preferences.service.PreferencesServiceImpl;
import com.codurance.sessionize.sessionizeservice.user.repository.CustomUserRepository;
import com.codurance.sessionize.sessionizeservice.user.repository.CustomUserRepositoryImpl;
import com.codurance.sessionize.sessionizeservice.user.repository.UserRepository;
import org.modelmapper.ModelMapper;
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

	@Bean
	public PreferencesService preferencesService(PreferencesRepository preferencesRepository, ModelMapper modelMapper) {
		return new PreferencesServiceImpl(preferencesRepository, modelMapper);
	}

	@Bean
	public PreferencesRepository preferencesRepository(UserRepository userRepository) {
		return new PreferencesRepositoryImpl(userRepository);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
