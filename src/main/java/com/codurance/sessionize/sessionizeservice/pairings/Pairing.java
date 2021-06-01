package com.codurance.sessionize.sessionizeservice.pairings;


import com.codurance.sessionize.sessionizeservice.preferences.Language;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "pairings")
public class Pairing {
    @Id private String id;
    @Transient private String partnerUserId;
    private List<String> users;
    private Language language;
    private String time;
    private String date;
    private Status status;
}
