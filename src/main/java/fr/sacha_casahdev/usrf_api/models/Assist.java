package fr.sacha_casahdev.usrf_api.models;

import lombok.Getter;
import lombok.Setter;

import java.sql.Time;

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

    public Assist(Played player, Played assisted_player, Time assist_time, AssistType assist_type) {
        this.player = player;
        this.assisted_player = assisted_player;
        this.assist_time = assist_time;
        this.assist_type = assist_type;
    }

    @Override
    public String toJson() {
        return "{" +
                "\"id\":" + id + "," +
                "\"player\":" + player.toJson() + "," +
                "\"assisted_player\":" + assisted_player.toJson() + "," +
                "\"assist_time\":\"" + assist_time + "\"," +
                "\"assist_type\":\"" + assist_type + "\"" +
                "}";
    }
}
