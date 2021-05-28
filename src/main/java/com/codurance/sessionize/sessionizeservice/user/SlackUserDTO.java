package com.codurance.sessionize.sessionizeservice.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SlackUserDTO {

    String slackUser;
    String email;
    String firstName;
    String lastName;
    boolean optIn;

}
