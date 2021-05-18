package com.codurance.sessionize.sessionizeservice;

import com.codurance.sessionize.sessionizeservice.authentication.TokenVerification;
import com.codurance.sessionize.sessionizeservice.slack.SlackRestClient;
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

}
