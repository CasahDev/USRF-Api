package fr.sacha_casahdev.usrf_api.models;

import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.util.Map;

@Getter
@Setter
public class MatchHistory implements IJsonable {

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

    public MatchHistory() {
    }

    @Override
    public String toJson() {
        return "{\"id\":" + id +
                ",\"match\":" + match.toJson() +
                ",\"event\":" + event.name() +
                ",\"time\":\"" + time +
                "\",\"additional_information\":\"" + additional_information +
                "\"}";
    }

    @Override
    public void fromJson(Map<String, Object> json) {
        if (json.containsKey("id")) {
            id = (int) json.get("id");
        }
        if (json.containsKey("match")) {
            match = new Match();
            match.fromJson((Map<String, Object>) json.get("match"));
        }
        if (json.containsKey("event")) {
            event = MatchEvent.valueOf((String) json.get("event"));
        }
        if (json.containsKey("time")) {
            time = Time.valueOf((String) json.get("time"));
        }
        if (json.containsKey("additional_information")) {
            additional_information = (String) json.get("additional_information");
        }
    }
}
