package com.codurance.sessionize.sessionizeservice.user;

public class SlackUserDTO {

    String slackId;
    String email;
    String firstName;
    String lastName;

    public String getSlackId() {
        return slackId;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setSlackId(String slackId) {
        this.slackId = slackId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
