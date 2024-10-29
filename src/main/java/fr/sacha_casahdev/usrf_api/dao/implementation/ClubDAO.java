package fr.sacha_casahdev.usrf_api.dao.implementation;

import fr.sacha_casahdev.usrf_api.dao.DBConnection;
import fr.sacha_casahdev.usrf_api.dao.interfaces.IClubDAO;
import fr.sacha_casahdev.usrf_api.models.Club;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Component("ClubDAO")
public class ClubDAO implements IClubDAO {
    @Override
    public ResponseEntity<List<Club>> getClubs(int page, int limit) {
        ResponseEntity<List<Club>> response;
        try {
            Connection conn = DBConnection.getConnection();

            if (conn == null) {
                throw new RuntimeException("Internal server error");
            }

            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM clubs LIMIT ? OFFSET ?");
            stmt.setInt(1, limit);
            stmt.setInt(2, (page - 1) * limit);

            ResultSet rs = stmt.executeQuery();

            // Get clubs from database
            response = ResponseEntity
                    .ok()
                    .header("Content-Type", "application/json")
                    .body(ResultSetToList(rs));
        } catch (Exception e) {
            response = ResponseEntity
                    .status(500)
                    .header("Content-Type", "application/json")
                    .header("error", e.getMessage())
                    .body(null);
        }

        return response;
    }

    @Override
    public ResponseEntity<Club> getClubById(int id) {
        ResponseEntity<Club> response;

        try {
            Connection conn = DBConnection.getConnection();

            if (conn == null) {
                throw new RuntimeException("Internal server error");
            }

            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM club WHERE id = ?");
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (!rs.next()) {
                throw new RuntimeException("Club not found");
            }

            Club club = new Club();
            club.setId(rs.getInt("id"));
            club.setName(rs.getString("name"));
            club.setLogoUrl(rs.getString("logo_url"));

            response = ResponseEntity
                    .ok()
                    .header("Content-Type", "application/json")
                    .body(club);
        } catch (Exception e) {
            response = ResponseEntity
                    .status(500)
                    .header("Content-Type", "application/json")
                    .header("error", e.getMessage())
                    .body(null);
        }

        return response;
    }

    @Override
    public ResponseEntity<List<Club>> getClubByName(String name) {
        ResponseEntity<List<Club>> response;

        try {
            Connection conn = DBConnection.getConnection();

            if (conn == null) {
                throw new RuntimeException("Internal server error");
            }

            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM club WHERE name = ?");
            stmt.setString(1, name);

            ResultSet rs = stmt.executeQuery();

            response = ResponseEntity
                    .ok()
                    .header("Content-Type", "application/json")
                    .body(ResultSetToList(rs));
        } catch (Exception e) {
            response = ResponseEntity
                    .status(500)
                    .header("Content-Type", "application/json")
                    .header("error", e.getMessage())
                    .body(null);
        }


        return response;
    }

    @Override
    public ResponseEntity<String> updateClub(int id, Club club) {
        ResponseEntity<String> response;

        try {
            Connection conn = DBConnection.getConnection();

            if (conn == null) {
                throw new RuntimeException("Internal server error");
            }

            PreparedStatement stmt = conn.prepareStatement("UPDATE club SET name = ?, logo_url = ? WHERE id = ?");
            stmt.setString(1, club.getName());
            stmt.setString(2, club.getLogoUrl());
            stmt.setInt(3, id);

            int rows = stmt.executeUpdate();

            if (rows == 0) {
                throw new RuntimeException("Club not found");
            }

            response = ResponseEntity
                    .ok()
                    .header("Content-Type", "application/json")
                    .body("Club updated");
        } catch (Exception e) {
            response = ResponseEntity
                    .status(500)
                    .header("Content-Type", "application/json")
                    .header("error", e.getMessage())
                    .body(null);
        }

        return response;
    }

    @Override
    public ResponseEntity<String> deleteClub(int id) {
        ResponseEntity<String> response;

        try {
            Connection conn = DBConnection.getConnection();

            if (conn == null) {
                throw new RuntimeException("Internal server error");
            }

            PreparedStatement stmt = conn.prepareStatement("DELETE FROM club WHERE id = ?");
            stmt.setInt(1, id);

            int rows = stmt.executeUpdate();

            if (rows == 0) {
                throw new RuntimeException("Club not found");
            }

            response = ResponseEntity
                    .ok()
                    .header("Content-Type", "application/json")
                    .body("Club deleted");
        } catch (Exception e) {
            response = ResponseEntity
                    .status(500)
                    .header("Content-Type", "application/json")
                    .header("error", e.getMessage())
                    .body(null);
        }

        return response;
    }

    @Override
    public ResponseEntity<Club> createClub(Club club) {
        ResponseEntity<Club> response;

        try {
            Connection conn = DBConnection.getConnection();

            if (conn == null) {
                throw new RuntimeException("Internal server error");
            }

            PreparedStatement stmt = conn.prepareStatement("INSERT INTO club (name, logo_url) VALUES (?, ?, ?)");
            stmt.setString(2, club.getName());
            stmt.setString(3, club.getLogoUrl());

            int rows = stmt.executeUpdate();

            if (rows == 0) {
                throw new RuntimeException("Club not created");
            }

            ResultSet rs = stmt.executeQuery();

            if (!rs.next()) {
                throw new RuntimeException("Club not created");
            }

            int id = stmt.getGeneratedKeys().getInt(1);
            club.setId(id);

            response = ResponseEntity
                    .ok()
                    .header("Content-Type", "application/json")
                    .body(club);
        } catch (Exception e) {
            response = ResponseEntity
                    .status(500)
                    .header("Content-Type", "application/json")
                    .header("error", e.getMessage())
                    .body(null);
        }

        return response;
    }

    private List<Club> ResultSetToList(ResultSet rs) {
        List<Club> clubs = new ArrayList<>();
        try {
            while (rs.next()) {
                Club club = new Club();
                club.setId(rs.getInt("id"));
                club.setName(rs.getString("name"));
                club.setLogoUrl(rs.getString("logo_url"));
                clubs.add(club);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return clubs;
    }
}
