package fr.sacha_casahdev.usrf_api.models;

import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.util.Map;

@Getter
@Setter
public class Assist implements IJsonable {
    public int id = 0;
    public Played player = null;
    public Played assisted_player = null;
    private Time assist_time = null;
    private AssistType assist_type = AssistType.PASS;

    public Assist() {
    }

    public Assist(int id, Played player, Played assisted_player, Time assist_time, AssistType assist_type) {
        this.id = id;
        this.player = player;
        this.assisted_player = assisted_player;
        this.assist_time = assist_time;
        this.assist_type = assist_type;
    }

    @Override
    public String toJson() {
        return "{" +
                "\"id\":" + id + "," +
                "\"player\":" + player + "," +
                "\"assisted_player\":" + assisted_player + "," +
                "\"assist_time\":\"" + assist_time + "\"," +
                "\"assist_type\":\"" + assist_type + "\"" +
                "}";
    }

    @Override
    public void fromJson(Map<String, Object> json) {
        id = (int) json.get("id");
        player = (Played) json.get("player");
        assisted_player = (Played) json.get("assisted_player");
        assist_time = (Time) json.get("assist_time");
        assist_type = (AssistType) json.get("assist_type");
    }
}
