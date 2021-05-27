package com.codurance.sessionize.sessionizeservice.pairings;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class Pairing {
    private String familyName;
    private String givenName;
    private String imageUrl;
    private String language;
    private LocalTime time;
    private LocalDate date;
    private Status status;

    public Pairing() {
        this.familyName = "Dako";
        this.givenName = "Andras";
        this.imageUrl = "https://lh3.googleusercontent.com/a/AATXAJwGqOSdJZfqW9qCy7v-0M-ohyoEqHHZDSFV8FPQ=s96-c";
        this.language = "Java";
        this.time = LocalTime.parse("13:00");
        this.date = LocalDate.parse("2021-05-25");
        this.status = Status.PENDING;
    }


}
