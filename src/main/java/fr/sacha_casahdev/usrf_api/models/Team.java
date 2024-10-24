package fr.sacha_casahdev.usrf_api.models;

public class Team {
    private int id = 0;
    private int fff_id = 0;
    private String name = "";
    private Club club = null;

    public Team(int id, int fff_id, String name, Club club) {
        this.id = id;
        this.fff_id = fff_id;
        this.name = name;
        this.club = club;
    }

    public Team() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFff_id() {
        return fff_id;
    }

    public void setFff_id(int fff_id) {
        this.fff_id = fff_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }
}
