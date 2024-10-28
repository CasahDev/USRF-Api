package fr.sacha_casahdev.usrf_api.dao.implementation;

import fr.sacha_casahdev.usrf_api.dao.DBConnection;
import fr.sacha_casahdev.usrf_api.dao.interfaces.ICupDAO;
import fr.sacha_casahdev.usrf_api.models.Cup;
import fr.sacha_casahdev.usrf_api.models.Scale;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component("cupDAO")
public class CupDAO implements ICupDAO {
    Connection conn;

    public CupDAO() {
        this.conn = DBConnection.getConnection();
    }

    @Override
    public ResponseEntity<Cup> getCupById(int id) {
        ResponseEntity<Cup> response = null;

        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM cups WHERE id = ?");
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            Cup cup = new Cup();
            cup.setId(id);
            cup.setName(rs.getString("name"));
            cup.setScale(Scale.valueOf(rs.getString("scale")));

            response = ResponseEntity.ok(cup);
        } catch (Exception e) {
            response = ResponseEntity.internalServerError().build();
        }
        return response;
    }

    @Override
    public ResponseEntity<List<Cup>> getCups(int page, int limit) {
        ResponseEntity<List<Cup>> response = null;

        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM cups LIMIT ? OFFSET ?");
            stmt.setInt(1, limit);
            stmt.setInt(2, (page - 1) * limit);

            ResultSet rs = stmt.executeQuery();

            List<Cup> cups = CupListFromResultSet(rs);

            response = ResponseEntity.ok(cups);
        } catch (Exception e) {
            response = ResponseEntity.internalServerError().build();
        }

        return response;
    }

    private List<Cup> CupListFromResultSet(ResultSet rs) throws Exception {
        List<Cup> cups = new ArrayList<>();

        while (rs.next()) {
            Cup cup = new Cup();
            cup.setId(rs.getInt("id"));
            cup.setName(rs.getString("name"));
            cup.setScale(Scale.valueOf(rs.getString("scale")));
            cups.add(cup);
        }

        return cups;
    }

    @Override
    public ResponseEntity<List<Cup>> getCupsByScale(String scale, int page, int limit) {
        ResponseEntity<List<Cup>> response = null;

        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM cups WHERE scale = ? LIMIT ? OFFSET ?");
            stmt.setString(1, scale);
            stmt.setInt(2, limit);
            stmt.setInt(3, (page - 1) * limit);

            ResultSet rs = stmt.executeQuery();

            List<Cup> cups = CupListFromResultSet(rs);

            response = ResponseEntity.ok(cups);
        } catch (Exception e) {
            response = ResponseEntity.internalServerError().build();
        }

        return response;
    }

    @Override
    public ResponseEntity<Cup> addCup(Map<String, Object> cup) {
        ResponseEntity<Cup> response = null;

        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO cups (name, scale) VALUES (?, ?)");
            stmt.setString(1, (String) cup.get("name"));
            stmt.setString(2, (String) cup.get("scale"));

            stmt.executeUpdate();

            int id = stmt.getGeneratedKeys().getInt(1);

            Cup c = new Cup();
            c.setId(id);
            c.setName((String) cup.get("name"));
            c.setScale(Scale.valueOf((String) cup.get("scale")));

            response = ResponseEntity.ok(c);
        } catch (Exception e) {
            response = ResponseEntity.internalServerError().build();
        }

        return response;
    }

    @Override
    public ResponseEntity<Cup> updateCup(int id, Map<String, Object> cup) {
        ResponseEntity<Cup> response = null;

        try {
            PreparedStatement stmt = conn.prepareStatement("UPDATE cups SET name = ?, scale = ? WHERE id = ?");
            stmt.setString(1, (String) cup.get("name"));
            stmt.setString(2, (String) cup.get("scale"));
            stmt.setInt(3, (int) cup.get("id"));

            stmt.executeUpdate();

            Cup c = new Cup();
            c.setId(id);
            c.setName((String) cup.get("name"));
            c.setScale(Scale.valueOf((String) cup.get("scale")));

            response = ResponseEntity.ok(c);
        } catch (Exception e) {
            response = ResponseEntity.internalServerError().build();
        }

        return response;
    }

    @Override
    public ResponseEntity<String> deleteCup(int id) {
        ResponseEntity<String> response = null;

        try {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM cups WHERE id = ?");
            stmt.setInt(1, id);

            stmt.executeUpdate();

            response = ResponseEntity.ok("Cup deleted");
        } catch (Exception e) {
            response = ResponseEntity.internalServerError().build();
        }

        return response;
    }
}
