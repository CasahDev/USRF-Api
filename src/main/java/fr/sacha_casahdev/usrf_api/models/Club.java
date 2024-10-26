package fr.sacha_casahdev.usrf_api.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class Club implements IJsonable {

    private int id;
    private String name;
    private String logoUrl;

    public Club(int id, String name, int fffId, String logoUrl) {
        this.id = id;
        this.name = name;
        this.logoUrl = logoUrl;
    }

    public Club() {}

    public String toJson() {
        return "{" +
                "\"id\":" + id +
                ", \"name\":\"" + name + "\"" +
                ", \"logoUrl\":\"" + logoUrl + "\"" +
                "}";
    }

    @Override
    public void fromJson(Map<String, Object> json) {
        id = (int) json.get("id");
        name = (String) json.get("name");
        logoUrl = (String) json.get("logoUrl");
    }
}
