package fr.sacha_casahdev.usrf_api.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CanPlay {
    private int id = 0;
    private Player player = null;
    private Positions position = null;

    public CanPlay() {
    }

    public CanPlay(Player player, Positions position) {
        this.player = player;
        this.position = position;
    }
}
