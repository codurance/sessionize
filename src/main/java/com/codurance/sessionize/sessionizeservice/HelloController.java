package com.codurance.sessionize.sessionizeservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }

    @PostMapping("/isNewUser")
    public boolean isNewUser(SlackUserIdentity user) {
        logger.info(user.id);
        logger.info(user.name);
        logger.info(user.email);
        return true;
    }

    private class SlackUserIdentity {
        String id;
        String name;
        String email;
    }
}
