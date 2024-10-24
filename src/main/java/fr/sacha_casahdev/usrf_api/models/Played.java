package fr.sacha_casahdev.usrf_api.models;

import lombok.Getter;
import lombok.Setter;

import java.sql.Time;

@Getter
@Setter
public class Played {
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
}
