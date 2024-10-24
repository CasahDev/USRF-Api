package fr.sacha_casahdev.usrf_api.models;

import java.util.Date;
import java.util.HashMap;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public boolean isIs_admin() {
        return is_admin;
    }

    public void setIs_admin(boolean is_admin) {
        this.is_admin = is_admin;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Club getRelated_to() {
        return related_to;
    }

    public void setRelated_to(Club related_to) {
        this.related_to = related_to;
    }
}
