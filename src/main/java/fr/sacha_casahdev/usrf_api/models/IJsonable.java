package fr.sacha_casahdev.usrf_api.models;

import java.util.Map;

public interface IJsonable {
    String toJson();
    void fromJson(Map<String, Object> json);
}
