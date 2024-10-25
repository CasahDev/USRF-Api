package fr.sacha_casahdev.usrf_api.models;

import lombok.Getter;
import lombok.Setter;

import java.sql.Time;

@Getter
@Setter
public class History implements IJsonable{
    private int id = 0;
    private User author = null;
    private Time created_at = null;
    private ActionType action = null;
    private String additional_information = null;

    public History() {
    }

    public History(User author, Time created_at, ActionType action, String additional_information) {
        this.author = author;
        this.created_at = created_at;
        this.action = action;
        this.additional_information = additional_information;
    }

    @Override
    public String toJson() {
        return "{" +
                "\"id\":" + id +
                ", \"author\":" + author.toJson() +
                ", \"created_at\":\"" + created_at + "\"" +
                ", \"action\":\"" + action + "\"" +
                ", \"additional_information\":\"" + additional_information + "\"" +
                "}";
    }
}
