package fr.sacha_casahdev.usrf_api.dao.implementation;

import fr.sacha_casahdev.usrf_api.dao.DBConnection;
import fr.sacha_casahdev.usrf_api.dao.interfaces.IPlayerDAO;
import fr.sacha_casahdev.usrf_api.models.Player;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component("PlayerDAO")
public class PlayerDAO implements IPlayerDAO {
    private Connection conn;

    public PlayerDAO() {
        this.conn = DBConnection.getConnection();
    }

    @Override
    public ResponseEntity<List<Player>> getPlayers(int page, int limit) {
        ResponseEntity<List<Player>> response;

        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM players LIMIT ? OFFSET ?");
            stmt.setInt(1, limit);
            stmt.setInt(2, (page - 1) * limit);

            response = ResponseEntity.ok().header("content-type", "application/json").body(ListFromResultSet(stmt.executeQuery()));

        } catch (Exception e) {
            response = ResponseEntity
                    .internalServerError()
                    .header("error", e.getMessage())
                    .body(null);
        }

        return response;
    }

    private List<Player> ListFromResultSet(ResultSet resultSet) {
        List<Player> players = new ArrayList<>();

        try {
            while (resultSet.next()) {
                Player player = new Player();
                player.setId(resultSet.getInt("id"));
                player.setFirst_name(resultSet.getString("first_name"));
                player.setLast_name(resultSet.getString("last_name"));
                player.setAge(resultSet.getInt("age"));
                players.add(player);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return players;
    }

    @Override
    public ResponseEntity<Player> getPlayerById(int id) {
        ResponseEntity<Player> response;

        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM players WHERE id = ?");
            stmt.setInt(1, id);

            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                Player player = new Player();
                player.setId(resultSet.getInt("id"));
                player.setFirst_name(resultSet.getString("first_name"));
                player.setLast_name(resultSet.getString("last_name"));
                player.setAge(resultSet.getInt("age"));
                response = ResponseEntity.ok().header("content-type", "application/json").body(player);
            } else {
                response = ResponseEntity.notFound().header("error", "Player not found").build();
            }
        } catch (Exception e) {
            response = ResponseEntity
                    .internalServerError()
                    .header("error", e.getMessage())
                    .body(null);
        }

        return response;
    }

    @Override
    public ResponseEntity<Player> createPlayer(Map<String, Object> player) {
        ResponseEntity<Player> response;

        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO players (first_name, last_name, age) VALUES (?, ?, ?)");
            stmt.setString(1, player.get("first_name").toString());
            stmt.setString(2, player.get("last_name").toString());
            stmt.setInt(3, (int) player.get("age"));

            stmt.executeUpdate();

            int id = stmt.getGeneratedKeys().getInt(1);

            Player p = new Player();
            p.setId(id);
            p.setFirst_name(player.get("first_name").toString());
            p.setLast_name(player.get("last_name").toString());
            p.setAge((int) player.get("age"));

            response = ResponseEntity.ok().header("content-type", "application/json").body(p);
        } catch (Exception e) {
            response = ResponseEntity
                    .internalServerError()
                    .header("error", e.getMessage())
                    .body(null);
        }

        return response;
    }

    @Override
    public ResponseEntity<Player> updatePlayer(int id, Map<String, Object> player) {
        ResponseEntity<Player> response;

        try {
            PreparedStatement stmt = conn.prepareStatement("UPDATE players SET first_name = ?, last_name = ?, age = ? WHERE id = ?");
            stmt.setString(1, player.get("first_name").toString());
            stmt.setString(2, player.get("last_name").toString());
            stmt.setInt(3, (int) player.get("age"));
            stmt.setInt(4, id);

            stmt.executeUpdate();

            Player p = new Player();
            p.setId(id);
            p.setFirst_name(player.get("first_name").toString());
            p.setLast_name(player.get("last_name").toString());
            p.setAge((int) player.get("age"));

            response = ResponseEntity.ok().header("content-type", "application/json").body(p);
        } catch (Exception e) {
            response = ResponseEntity
                    .internalServerError()
                    .header("error", e.getMessage())
                    .body(null);
        }

        return response;
    }

    @Override
    public ResponseEntity<Player> deletePlayer(int id) {
        ResponseEntity<Player> response;

        try {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM players WHERE id = ?");
            stmt.setInt(1, id);

            stmt.executeUpdate();

            response = ResponseEntity.ok().header("content-type", "application/json").body(null);
        } catch (Exception e) {
            response = ResponseEntity
                    .internalServerError()
                    .header("error", e.getMessage())
                    .body(null);
        }

        return response;
    }
}
