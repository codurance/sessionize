package com.codurance.sessionize.sessionizeservice.users;

public class SessionizeUser {
    private final String name;
    private final String email;

    public SessionizeUser(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
