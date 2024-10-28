package fr.sacha_casahdev.usrf_api.models;

import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.util.Map;

@Getter
@Setter
public class Penalty implements IJsonable {

    private int id = 0;
    private Played player = null;
    private Time penalty_time = null;
    private PenaltyResult result = PenaltyResult.SCORED;
    private PenaltyObtainingMethod obtaining_method = PenaltyObtainingMethod.FOUL;

    public Penalty() {
    }

    public Penalty(int id, Played player, Time penalty_time, PenaltyResult result, PenaltyObtainingMethod obtaining_method) {
        this.id = id;
        this.player = player;
        this.penalty_time = penalty_time;
        this.result = result;
        this.obtaining_method = obtaining_method;
    }

    @Override
    public String toJson() {
        return "{\"id\":" + id +
                ",\"player\":" + player.toJson() +
                ",\"penalty_time\":\"" + penalty_time +
                "\",\"result\":\"" + result.name() +
                "\",\"obtaining_method\":\"" + obtaining_method.name() +
                "\"}";
    }

    @Override
    public void fromJson(Map<String, Object> json) {
        if (json.containsKey("id")) {
            id = (int) json.get("id");
        }
        if (json.containsKey("player")) {
            player = new Played();
            player.fromJson((Map<String, Object>) json.get("player"));
        }
        if (json.containsKey("penalty_time")) {
            penalty_time = Time.valueOf((String) json.get("penalty_time"));
        }
        if (json.containsKey("result")) {
            result = PenaltyResult.valueOf((String) json.get("result"));
        }
        if (json.containsKey("obtaining_method")) {
            obtaining_method = PenaltyObtainingMethod.valueOf((String) json.get("obtaining_method"));
        }
    }
}
