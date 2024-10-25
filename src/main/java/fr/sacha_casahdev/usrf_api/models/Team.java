package fr.sacha_casahdev.usrf_api.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Team implements IJsonable {
    private int id = 0;
    private int fff_id = 0;
    private String name = "";
    private Club club = null;

    public Team(int id, int fff_id, String name, Club club) {
        this.id = id;
        this.fff_id = fff_id;
        this.name = name;
        this.club = club;
    }

    public Team() {}

    public String toJson() {
        return "{" +
                "\"id\":" + id + "," +
                "\"fff_id\":" + fff_id + "," +
                "\"name\":\"" + name + "\"," +
                "\"club\":" + club.toJson() +
                "}";
    }
}
