package fr.sacha_casahdev.usrf_api.models;

import lombok.Getter;
import lombok.Setter;

import java.sql.Time;

@Getter
@Setter
public class Played implements IJsonable {
    private int id = 0;
    private Match match = null;
    private Player player = null;
    private int jersey_number = 0;
    private Time entry_time = null;
    private Time exit_time = null;
    private int goals = 0;
    private int blocked_shots = 0;
    private int on_target_shots = 0;
    private int off_target_shots = 0;
    private boolean yellow_card = false;
    private boolean red_card = false;
    private boolean is_injured = false;
    private boolean is_capitain = false;
    private Positions position = Positions.Substitute;

    public Played() {
    }

    public Played(int id, Match match, Player player, int jersey_number, Time entry_time, Time exit_time, int goals, int blocked_shots, int on_target_shots, int off_target_shots, boolean yellow_card, boolean red_card, boolean is_injured, boolean is_capitain, Positions position) {
        this.id = id;
        this.match = match;
        this.player = player;
        this.jersey_number = jersey_number;
        this.entry_time = entry_time;
        this.exit_time = exit_time;
        this.goals = goals;
        this.blocked_shots = blocked_shots;
        this.on_target_shots = on_target_shots;
        this.off_target_shots = off_target_shots;
        this.yellow_card = yellow_card;
        this.red_card = red_card;
        this.is_injured = is_injured;
        this.is_capitain = is_capitain;
        this.position = position;
    }


    @Override
    public String toJson() {
        return "{" +
                "\"id\":" + id + "," +
                "\"match\":" + match.toJson() + "," +
                "\"player\":" + player.toJson() + "," +
                "\"jersey_number\":" + jersey_number + "," +
                "\"entry_time\":\"" + entry_time + "\"," +
                "\"exit_time\":\"" + exit_time + "\"," +
                "\"goals\":" + goals + "," +
                "\"blocked_shots\":" + blocked_shots + "," +
                "\"on_target_shots\":" + on_target_shots + "," +
                "\"off_target_shots\":" + off_target_shots + "," +
                "\"yellow_card\":" + yellow_card + "," +
                "\"red_card\":" + red_card + "," +
                "\"is_injured\":" + is_injured + "," +
                "\"is_capitain\":" + is_capitain + "," +
                "\"position\":\"" + position + "\"" +
                "}";
    }
}
