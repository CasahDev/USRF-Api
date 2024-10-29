package fr.sacha_casahdev.usrf_api.dao.implementation;

import fr.sacha_casahdev.usrf_api.dao.DBConnection;
import fr.sacha_casahdev.usrf_api.dao.interfaces.ITeamDAO;
import fr.sacha_casahdev.usrf_api.models.Team;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component("TeamDAO")
public class TeamDAO implements ITeamDAO {
    Connection conn;

    public TeamDAO() {
        this.conn = DBConnection.getConnection();
    }

    @Override
    public ResponseEntity<Team> getTeamById(int id) {
        ResponseEntity<Team> response;

        try {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM teams WHERE id = ?");
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                Team team = new Team(rs.getInt("id"), rs.getString("name"), new ClubDAO().getClubById(rs.getInt("club_id")).getBody());
                response = ResponseEntity.ok(team);
            } else {
                response = ResponseEntity.notFound().build();
            }

        } catch (Exception e) {
            response = ResponseEntity.status(500).body(null);
        }

        return response;
    }

    @Override
    public ResponseEntity<List<Team>> getTeams(int page, int limit) {
        ResponseEntity<List<Team>> response;

        try {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM teams LIMIT ? OFFSET ?");
            statement.setInt(1, limit);
            statement.setInt(2, (page - 1) * limit);

            ResultSet rs = statement.executeQuery();

            List<Team> teams = new ArrayList<>();
            while (rs.next()) {
                Team team = new Team(rs.getInt("id"), rs.getString("name"), new ClubDAO().getClubById(rs.getInt("club_id")).getBody());
                teams.add(team);
            }

            response = ResponseEntity.ok(teams);
        } catch (Exception e) {
            response = ResponseEntity.status(500).body(null);
        }

        return response;
    }

    @Override
    public ResponseEntity<Team> addTeam(Map<String, Object> team) {
        ResponseEntity<Team> response;

        try {
            PreparedStatement statement = conn.prepareStatement("INSERT INTO teams (name, club_id) VALUES (?, ?)");
            statement.setString(1, (String) team.get("name"));
            statement.setInt(2, (int) team.get("club_id"));

            statement.executeUpdate();

            response = ResponseEntity.ok(new Team((int) team.get("id"), (String) team.get("name"), new ClubDAO().getClubById((int) team.get("club_id")).getBody()));
        } catch (Exception e) {
            response = ResponseEntity.status(500).body(null);
        }

        return response;
    }

    @Override
    public ResponseEntity<Team> updateTeam(Map<String, Object> team) {
        ResponseEntity<Team> response;

        try {
            PreparedStatement statement = conn.prepareStatement("UPDATE teams SET name = ?, club_id = ? WHERE id = ?");
            statement.setString(1, (String) team.get("name"));
            statement.setInt(2, (int) team.get("club_id"));
            statement.setInt(3, (int) team.get("id"));

            statement.executeUpdate();

            response = ResponseEntity.ok(new Team((int) team.get("id"), (String) team.get("name"), new ClubDAO().getClubById((int) team.get("club_id")).getBody()));
        } catch (Exception e) {
            response = ResponseEntity.status(500).body(null);
        }

        return response;
    }

    @Override
    public ResponseEntity<String> deleteTeam(int id) {
        ResponseEntity<String> response;

        try {
            PreparedStatement statement = conn.prepareStatement("DELETE FROM teams WHERE id = ?");
            statement.setInt(1, id);

            statement.executeUpdate();

            response = ResponseEntity.ok("Team deleted");
        } catch (Exception e) {
            response = ResponseEntity.status(500).body(null);
        }

        return response;
    }
}
