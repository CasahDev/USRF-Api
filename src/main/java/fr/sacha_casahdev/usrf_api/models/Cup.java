package fr.sacha_casahdev.usrf_api.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class Cup implements IJsonable{
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

    @Override
    public String toJson() {
        return "{" +
                "\"id\":" + id + "," +
                "\"name\":\"" + name + "\"," +
                "\"description\":\"" + description + "\"," +
                "\"scale\":" + scale.name() +
                "}";
    }

    @Override
    public void fromJson(Map<String, Object> json) {
        if (json.containsKey("id")) id = (int) json.get("id");
        if (json.containsKey("name")) name = (String) json.get("name");
        if (json.containsKey("description")) description = (String) json.get("description");
        if (json.containsKey("scale")) scale = Scale.valueOf((String) json.get("scale"));
    }
}
