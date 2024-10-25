package fr.sacha_casahdev.usrf_api.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class CanPlay implements IJsonable {
    private int id = 0;
    private Player player = null;
    private Positions position = null;

    public CanPlay() {
    }

    public CanPlay(Player player, Positions position) {
        this.player = player;
        this.position = position;
    }

    @Override
    public String toJson() {
        return "{\"id\":" + id + ",\"player\":" + player.toJson() + ",\"position\":" + position.name() + "}";
    }

    @Override
    public void fromJson(Map<String, Object> json) {
        id = (int) json.get("id");
        player = (Player) json.get("player");
        position = (Positions) json.get("position");
    }
}
