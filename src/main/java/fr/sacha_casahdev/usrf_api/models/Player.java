package fr.sacha_casahdev.usrf_api.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Player implements IJsonable {
    private int id;
    private String first_name;
    private String last_name;

    public Player(int id, String first_name, String last_name) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public Player() {}

    public String toJson() {
        return "{" +
                "\"id\":" + id + "," +
                "\"first_name\":\"" + first_name + "\"," +
                "\"last_name\":\"" + last_name + "\"" +
                "}";
    }
}
