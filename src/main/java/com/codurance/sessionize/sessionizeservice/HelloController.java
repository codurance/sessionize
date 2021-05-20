package com.codurance.sessionize.sessionizeservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }

    @PostMapping("/isNewUser")
    public boolean isNewUser(@RequestBody SlackUserIdentity user) {
        try {
            logger.info(user.getId());
            logger.info(user.getName());
            logger.info(user.getEmail());
            return true;
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
    }

    private class SlackUserIdentity {
        String id;
        String name;
        String email;

        public String getId() { return id; }
        public String getName() { return name; }
        public String getEmail() { return email; }
    }
}
