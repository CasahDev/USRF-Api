package fr.sacha_casahdev.usrf_api.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class User {
    private String id = "";
    private String email = "";
    private String first_name = "";
    private String last_name = "";
    private String password = "";
    private String salt = "";
    private boolean is_admin = false;
    private Date created_at = new Date();
    private Club related_to = null;

    public User(){}

    public User(String id, String email, String first_name, String last_name, String password, String salt, boolean is_admin, Date created_at, Club related_to){
        this.id = id;
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.password = password;
        this.salt = salt;
        this.is_admin = is_admin;
        this.created_at = created_at;
        this.related_to = related_to;
    }

}
