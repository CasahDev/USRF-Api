package fr.sacha_casahdev.usrf_api.models;

import lombok.Getter;
import lombok.Setter;

import java.sql.Time;

@Getter
@Setter
public class Penalty {
    private int id = 0;
    private Played player = null;
    private Time penalty_time = null;
    private PenaltyResult result = PenaltyResult.SCORED;
    private PenaltyObtainingMethod obtaining_method = PenaltyObtainingMethod.FOUL;

    public Penalty() {
    }

    public Penalty(Played player, Time penalty_time, PenaltyResult result, PenaltyObtainingMethod obtaining_method) {
        this.player = player;
        this.penalty_time = penalty_time;
        this.result = result;
        this.obtaining_method = obtaining_method;
    }
}
