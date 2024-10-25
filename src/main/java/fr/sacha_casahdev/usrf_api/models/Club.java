package fr.sacha_casahdev.usrf_api.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Club implements IJsonable {
    private int id;
    private String name;
    private int fffId;
    private String logoUrl;

    public Club(int id, String name, int fffId, String logoUrl) {
        this.id = id;
        this.name = name;
        this.fffId = fffId;
        this.logoUrl = logoUrl;
    }

    public Club() {}

    public String toJson() {
        return "{" +
                "\"id\":" + id +
                ", \"name\":\"" + name + "\"" +
                ", \"fffId\":" + fffId +
                ", \"logoUrl\":\"" + logoUrl + "\"" +
                "}";
    }
}
