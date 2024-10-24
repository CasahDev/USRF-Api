package fr.sacha_casahdev.usrf_api.models;

import lombok.Getter;
import lombok.Setter;

import java.sql.Time;

@Getter
@Setter
public class MatchHistory {
    private int id = 0;
    private Match match = null;
    private MatchEvent event = null;
    private Time time = null;
    private String additional_information = null;

    public MatchHistory(int id, Match match, MatchEvent event, Time time, String additional_information) {
        this.id = id;
        this.match = match;
        this.event = event;
        this.time = time;
        this.additional_information = additional_information;
    }

    public MatchHistory(Match match, MatchEvent event, Time time, String additional_information) {
        this.match = match;
        this.event = event;
        this.time = time;
        this.additional_information = additional_information;
    }
}
