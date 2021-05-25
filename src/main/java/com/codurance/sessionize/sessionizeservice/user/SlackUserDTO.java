package com.codurance.sessionize.sessionizeservice.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SlackUserDTO {

    String slackId;
    String email;
    String firstName;
    String lastName;
    boolean optOut;

}
