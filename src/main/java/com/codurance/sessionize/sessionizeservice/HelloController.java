package com.codurance.sessionize.sessionizeservice;

import com.codurance.sessionize.sessionizeservice.user.SlackUserDTO;
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
    public boolean isNewUser(@RequestBody SlackUserDTO user) {
        try {
            logger.info(user.getId());
            logger.info(user.getName());
            logger.info(user.getEmail());
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return true;
    }
}
