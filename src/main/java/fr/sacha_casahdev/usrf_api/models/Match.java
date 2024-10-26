package fr.sacha_casahdev.usrf_api.models;

import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.util.Map;

@Getter
@Setter
public class Match implements IJsonable {
    private int id = 0;
    private Team team1 = null;
    private Team team2 = null;
    private int score1 = 0;
    private int score2 = 0;
    private String address = "";
    private Time date = null;
    private boolean is_home = false;
    private String coach = "";
    private GameState state = GameState.NOT_STARTED;
    private Time started_time = null;
    private Cup cup = null;

    public Match() {
    }

    public Match(int id, Team team1, Team team2, int score1, int score2, String address, Time date, boolean is_home, String coach, GameState state, Time started_time, Cup cup) {
        this.id = id;
        this.team1 = team1;
        this.team2 = team2;
        this.score1 = score1;
        this.score2 = score2;
        this.address = address;
        this.date = date;
        this.is_home = is_home;
        this.coach = coach;
        this.state = state;
        this.started_time = started_time;
        this.cup = cup;
    }

    @Override
    public String toJson() {
        return "{" +
                "\"id\":" + id + "," +
                "\"team1\":" + team1.toJson() + "," +
                "\"team2\":" + team2.toJson() + "," +
                "\"score1\":" + score1 + "," +
                "\"score2\":" + score2 + "," +
                "\"address\":\"" + address + "\"," +
                "\"date\":\"" + date + "\"," +
                "\"is_home\":" + is_home + "," +
                "\"coach\":\"" + coach + "\"," +
                "\"state\":\"" + state + "\"," +
                "\"started_time\":\"" + started_time + "\"," +
                "\"cup\":" + cup +
                "}";
    }

    @Override
    public void fromJson(Map<String, Object> json) {
        if (json.containsKey("id")) id = (int) json.get("id");
        if (json.containsKey("team1")) {
            Team t = new Team();
            t.fromJson((Map<String, Object>) json.get("team1"));
            team1 = t;
        }
        if (json.containsKey("team2")) {
            Team t = new Team();
            t.fromJson((Map<String, Object>) json.get("team2"));
            team2 = t;
        }
        if (json.containsKey("score1")) score1 = (int) json.get("score1");
        if (json.containsKey("score2")) score2 = (int) json.get("score2");
        if (json.containsKey("address")) address = (String) json.get("address");
        if (json.containsKey("date")) date = Time.valueOf((String) json.get("date"));
        if (json.containsKey("is_home")) is_home = (boolean) json.get("is_home");
        if (json.containsKey("coach")) coach = (String) json.get("coach");
        if (json.containsKey("state")) state = GameState.valueOf((String) json.get("state"));
        if (json.containsKey("started_time")) started_time = Time.valueOf((String) json.get("started_time"));
        if (json.containsKey("cup")) {
            Cup c = new Cup();
            c.fromJson((Map<String, Object>) json.get("cup"));
            cup = c;
        }
    }
}
