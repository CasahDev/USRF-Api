package fr.sacha_casahdev.usrf_api.models;

import lombok.Getter;
import lombok.Setter;

import java.sql.Time;

@Getter
@Setter
public class Match {
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
}
