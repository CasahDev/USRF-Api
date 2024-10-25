package fr.sacha_casahdev.usrf_api.models;

import lombok.Getter;
import lombok.Setter;

import java.sql.Time;

@Getter
@Setter
public class Match implements IJsonable {
    private int id = 0;
    private Team team = null;
    private Team opponent = null;
    private int score = 0;
    private int opponent_score = 0;
    private String address = "";
    private Time date = null;
    private boolean is_home = false;
    private String coach = "";
    private GameState state = GameState.notStarted;
    private Time started_time = null;
    private boolean is_cup;

    public Match() {
    }

    public Match(int id, Team team, Team opponent, int score, int opponent_score, String address, Time date, boolean is_home, String coach, GameState state, Time started_time, boolean is_cup) {
        this.id = id;
        this.team = team;
        this.opponent = opponent;
        this.score = score;
        this.opponent_score = opponent_score;
        this.address = address;
        this.date = date;
        this.is_home = is_home;
        this.coach = coach;
        this.state = state;
        this.started_time = started_time;
        this.is_cup = is_cup;
    }

    @Override
    public String toJson() {
        return "{" +
                "\"id\":" + id + "," +
                "\"team\":" + team.toJson() + "," +
                "\"opponent\":" + opponent.toJson() + "," +
                "\"score\":" + score + "," +
                "\"opponent_score\":" + opponent_score + "," +
                "\"address\":\"" + address + "\"," +
                "\"date\":\"" + date + "\"," +
                "\"is_home\":" + is_home + "," +
                "\"coach\":\"" + coach + "\"," +
                "\"state\":\"" + state + "\"," +
                "\"started_time\":\"" + started_time + "\"," +
                "\"is_cup\":" + is_cup +
                "}";
    }
}
