package com.codurance.sessionize.sessionizeservice;

import com.codurance.sessionize.sessionizeservice.infrastructure.health.SlackRestClient;
import com.codurance.sessionize.sessionizeservice.infrastructure.security.TokenVerification;
import com.codurance.sessionize.sessionizeservice.matching.service.MatchingService;
import com.codurance.sessionize.sessionizeservice.matching.service.MatchingServiceImpl;
import com.codurance.sessionize.sessionizeservice.matching.client.MatchingClient;
import com.codurance.sessionize.sessionizeservice.pairing.client.SlackClient;
import com.codurance.sessionize.sessionizeservice.pairing.repository.PairingsRepository;
import com.codurance.sessionize.sessionizeservice.pairing.service.PairingsService;
import com.codurance.sessionize.sessionizeservice.pairing.service.PairingsServiceImpl;
import com.codurance.sessionize.sessionizeservice.preferences.repository.CustomPreferencesRepository;
import com.codurance.sessionize.sessionizeservice.preferences.repository.CustomPreferencesRepositoryImpl;
import com.codurance.sessionize.sessionizeservice.preferences.service.PreferencesService;
import com.codurance.sessionize.sessionizeservice.preferences.service.PreferencesServiceImpl;
import com.codurance.sessionize.sessionizeservice.user.repository.CustomUserRepository;
import com.codurance.sessionize.sessionizeservice.user.repository.CustomUserRepositoryImpl;
import com.codurance.sessionize.sessionizeservice.user.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


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
    public TokenVerification tokenVerification() {
        return new TokenVerification();
    }

    @Bean
    public CustomUserRepository customUserRepository(UserRepository userRepository) {
        return new CustomUserRepositoryImpl(userRepository);
    }

    @Bean
    public PreferencesService preferencesService(CustomPreferencesRepository customPreferencesRepository, ModelMapper modelMapper) {
        return new PreferencesServiceImpl(customPreferencesRepository, modelMapper);
    }

    @Bean
    public CustomPreferencesRepository customPreferencesRepository(UserRepository userRepository) {
        return new CustomPreferencesRepositoryImpl(userRepository);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public MatchingClient matchingClient() {
        return new MatchingClient();
    }

    @Bean
    public MatchingService matchingService(MatchingClient matchingClient, CustomPreferencesRepository preferencesRepository, PairingsRepository pairingsRepository) {
        return new MatchingServiceImpl(matchingClient, preferencesRepository, pairingsRepository);
    }

    @Bean
    public PairingsService pairingsService(PairingsRepository pairingsRepository, ModelMapper modelMapper) {
        return new PairingsServiceImpl(pairingsRepository, modelMapper);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
