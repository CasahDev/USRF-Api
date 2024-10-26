package fr.sacha_casahdev.usrf_api.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class Player implements IJsonable {
    private int id;
    private String first_name;
    private String last_name;
    private int age;

    public Player(int id, String first_name, String last_name, int age) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.age = age;
    }

    public Player() {}

    public String toJson() {
        return "{" +
                "\"id\":" + id + "," +
                "\"first_name\":\"" + first_name + "\"," +
                "\"last_name\":\"" + last_name + "\"" +
                "}";
    }

    @Override
    public void fromJson(Map<String, Object> json) {
        this.id = (int) json.get("id");
        this.first_name = (String) json.get("first_name");
        this.last_name = (String) json.get("last_name");
        this.age = (int) json.get("age");
    }
}
