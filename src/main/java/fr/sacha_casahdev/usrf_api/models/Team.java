package fr.sacha_casahdev.usrf_api.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class Team implements IJsonable {
    private int id = 0;
    private String name = "";
    private Club club = null;

    public Team(int id, String name, Club club) {
        this.id = id;
        this.name = name;
        this.club = club;
    }

    public Team() {}

    public String toJson() {
        return "{" +
                "\"id\":" + id + "," +
                "\"name\":\"" + name + "\"," +
                "\"club\":" + club.toJson() +
                "}";
    }

    @Override
    public void fromJson(Map<String, Object> json) {
        if (json.containsKey("id")) {
            this.id = (int) json.get("id");
        }
        if (json.containsKey("name")) {
            this.name = (String) json.get("name");
        }
        if (json.containsKey("club")) {
            Club club = new Club();
            club.fromJson((Map<String, Object>) json.get("club"));
            this.club = club;
        }
    }
}
