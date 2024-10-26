package fr.sacha_casahdev.usrf_api.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cup {
    int id = 0;
    String name = "";
    String description = "";
    Scale scale = null;

    public Cup(int id, String name, String description, Scale scale) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.scale = scale;
    }

    public Cup() {}
}
