package fr.sacha_casahdev.usrf_api.dao.implementation;

import fr.sacha_casahdev.usrf_api.dao.DBConnection;
import fr.sacha_casahdev.usrf_api.dao.interfaces.IAssistDAO;
import fr.sacha_casahdev.usrf_api.models.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AssistDAO implements IAssistDAO {
    Connection conn;

    public AssistDAO() {
        this.conn = DBConnection.getConnection();
    }

    @Override
    public ResponseEntity<Assist> addAssists(Map<String, Object> assist) {
        ResponseEntity<Assist> response;

        try {
            String query = "INSERT INTO assists (match_id, player_id, assisted_played_id, assist_time, assist_type) " +
                    "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);

            int match_id = (int) assist.get("match_id");
            int player_id = (int) assist.get("player_id");
            int assisted_player_id = (int) assist.get("assisted_player_id");
            Time assist_time = (Time) assist.get("assist_time");
            String assist_type = (String) assist.get("assist_type");

            ps.setInt(1, match_id);
            ps.setInt(2, player_id);
            ps.setInt(3, assisted_player_id);
            ps.setTime(4, assist_time);
            ps.setString(5, assist_type);

            ps.executeQuery();

            int id = ps.getGeneratedKeys().getInt(1);

            Played player = new PlayedDAO().getPlayedByPlayerAndMatch(player_id, match_id).getBody();
            Played assisted_player = new PlayedDAO().getPlayedByPlayerAndMatch(assisted_player_id, match_id).getBody();

            response = ResponseEntity.ok(new Assist(id, player, assisted_player, assist_time, AssistType.valueOf(assist_type)));
        } catch (SQLException e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        return response;
    }

    @Override
    public ResponseEntity<String> removeAssists(int assist_id) {
        ResponseEntity<String> response;

        try {
            String query = "DELETE FROM assists WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, assist_id);
            ps.executeQuery();
            response = ResponseEntity.ok("Assist removed");
        } catch (SQLException e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        return response;
    }

    @Override
    public ResponseEntity<List<Assist>> getAssistsByUserAndGame(int idUser, int idGame) {
        ResponseEntity<List<Assist>> response;

        try {
            String query = "SELECT * FROM assists WHERE player_id = ? AND match_id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, idUser);
            ps.setInt(2, idGame);
            ps.executeQuery();

            ResultSet rs = ps.getResultSet();

            List<Assist> assists = AssistListFromResultSet(rs);

            response = ResponseEntity.ok(null);
        } catch (SQLException e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        return response;
    }

    private List<Assist> AssistListFromResultSet(ResultSet rs) throws SQLException {
        List<Assist> assists = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("id");
            int match_id = rs.getInt("match_id");
            int player_id = rs.getInt("player_id");
            int assisted_player_id = rs.getInt("assisted_player_id");
            Time assist_time = rs.getTime("assist_time");
            String assist_type = rs.getString("assist_type");

            Played player = new PlayedDAO().getPlayedByPlayerAndMatch(player_id, match_id).getBody();
            Played assisted_player = new PlayedDAO().getPlayedByPlayerAndMatch(assisted_player_id, match_id).getBody();

            assists.add(new Assist(id, player, assisted_player, assist_time, AssistType.valueOf(assist_type)));
        }

        return assists;
    }

    @Override
    public ResponseEntity<List<Assist>> getAssistsByUser(int idUser, int page, int limit) {
        ResponseEntity<List<Assist>> response;

        try {
            String query = "SELECT * FROM assists WHERE player_id = ? LIMIT ? OFFSET ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, idUser);
            ps.setInt(2, limit);
            ps.setInt(3, (page - 1) * limit);
            ps.executeQuery();

            ResultSet rs = ps.getResultSet();

            List<Assist> assists = AssistListFromResultSet(rs);

            response = ResponseEntity.ok(assists);
        } catch (SQLException e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        return response;
    }

    @Override
    public ResponseEntity<List<Assist>> getAssistsByGame(int idGame, int page, int limit) {
        ResponseEntity<List<Assist>> response;

        try {
            String query = "SELECT * FROM assists WHERE match_id = ? LIMIT ? OFFSET ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, idGame);
            ps.setInt(2, limit);
            ps.setInt(3, (page - 1) * limit);
            ps.executeQuery();

            ResultSet rs = ps.getResultSet();

            List<Assist> assists = AssistListFromResultSet(rs);

            response = ResponseEntity.ok(assists);
        } catch (SQLException e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        return response;
    }

    @Override
    public ResponseEntity<List<Assist>> getAssists(int page, int limit) {
        ResponseEntity<List<Assist>> response;

        try {
            String query = "SELECT * FROM assists LIMIT ? OFFSET ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, limit);
            ps.setInt(2, (page - 1) * limit);
            ps.executeQuery();

            ResultSet rs = ps.getResultSet();

            List<Assist> assists = AssistListFromResultSet(rs);

            response = ResponseEntity.ok(assists);
        } catch (SQLException e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        return response;
    }

    @Override
    public ResponseEntity<String> updateAssists(int idAssist, Map<String, Object> assist) {
        ResponseEntity<String> response;

        try {
            String query = "UPDATE assists SET match_id = ?, player_id = ?, assisted_played_id = ?, assist_time = ?, assist_type = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);

            int match_id = (int) assist.get("match_id");
            int player_id = (int) assist.get("player_id");
            int assisted_player_id = (int) assist.get("assisted_player_id");
            Time assist_time = (Time) assist.get("assist_time");
            String assist_type = (String) assist.get("assist_type");

            ps.setInt(1, match_id);
            ps.setInt(2, player_id);
            ps.setInt(3, assisted_player_id);
            ps.setTime(4, assist_time);
            ps.setString(5, assist_type);
            ps.setInt(6, idAssist);

            ps.executeQuery();

            response = ResponseEntity.ok("Assist updated");
        } catch (SQLException e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        return response;
    }

    @Override
    public ResponseEntity<String> deleteAssists(int idAssist) {
        ResponseEntity<String> response;

        try {
            String query = "DELETE FROM assists WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, idAssist);
            ps.executeQuery();
            response = ResponseEntity.ok("Assist deleted");
        } catch (SQLException e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        return response;
    }

    @Override
    public ResponseEntity<String> removeLastAssist(int game_id) {
        ResponseEntity<String> response;

        try {
            String query = "DELETE FROM assists WHERE id = (SELECT MAX(id) FROM assists WHERE match_id = ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, game_id);
            ps.executeQuery();
            response = ResponseEntity.ok("Last assist removed");
        } catch (SQLException e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        return response;
    }
}
