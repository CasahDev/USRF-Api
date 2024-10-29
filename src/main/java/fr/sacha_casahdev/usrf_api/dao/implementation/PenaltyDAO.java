package fr.sacha_casahdev.usrf_api.dao.implementation;

import fr.sacha_casahdev.usrf_api.dao.DBConnection;
import fr.sacha_casahdev.usrf_api.dao.interfaces.IPenaltyDAO;
import fr.sacha_casahdev.usrf_api.models.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component("PenaltyDAO")
public class PenaltyDAO implements IPenaltyDAO {
    Connection conn;

    public PenaltyDAO() {
        this.conn = DBConnection.getConnection();
    }

    @Override
    public ResponseEntity<Penalty> addPenalty(Map<String, Object> penalty) {
        ResponseEntity<Penalty> response;

        try {
            String query = "INSERT INTO penalties (game_id, played_id, penalty_time, penalty_type, penalty_obtaining_method) " +
                    "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);

            int game_id = (int) penalty.get("game_id");
            int played_id = (int) penalty.get("played_id");
            Time penalty_time = (Time) penalty.get("penalty_time");
            String penalty_result = (String) penalty.get("penalty_result");
            String penalty_obtaining_method = (String) penalty.get("penalty_obtaining_method");

            ps.setInt(1, game_id);
            ps.setInt(2, played_id);
            ps.setTime(3, penalty_time);
            ps.setString(4, penalty_result);
            ps.setString(5, penalty_obtaining_method);

            ps.executeQuery();

            int id = ps.getGeneratedKeys().getInt(1);
            Played player = new PlayedDAO().getPlayedByPlayerAndMatch(played_id, game_id).getBody();

            response = ResponseEntity.ok(new Penalty(id, player, penalty_time, PenaltyResult.valueOf(penalty_result), PenaltyObtainingMethod.valueOf(penalty_obtaining_method)));
        } catch (SQLException e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        return response;
    }

    @Override
    public ResponseEntity<String> removePenalty(int penalty_id) {
        ResponseEntity<String> response;

        try {
            String query = "DELETE FROM penalties WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setInt(1, penalty_id);

            ps.executeQuery();

            response = ResponseEntity.ok("Penalty removed");
        } catch (SQLException e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        return response;
    }

    @Override
    public ResponseEntity<List<Penalty>> getPenaltiesByUserAndGame(int idUser, int idGame) {
        ResponseEntity<List<Penalty>> response;

        try {
            String query = "SELECT * FROM penalties WHERE played_id = ? AND game_id = ?";
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setInt(1, idUser);
            ps.setInt(2, idGame);

            ps.executeQuery();

            ResultSet rs = ps.getResultSet();

            List<Penalty> penalties = PenaltyListFromResultSet(rs);

            response = ResponseEntity.ok(null);
        } catch (SQLException e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        return response;
    }

    private List<Penalty> PenaltyListFromResultSet(ResultSet rs) throws SQLException {
        List<Penalty> penalties = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("id");
            Played player = new PlayedDAO().getPlayedByPlayerAndMatch(rs.getInt("played_id"), rs.getInt("game_id")).getBody();
            Time penalty_time = rs.getTime("penalty_time");
            PenaltyResult penalty_result = PenaltyResult.valueOf(rs.getString("penalty_result"));
            PenaltyObtainingMethod penalty_obtaining_method = PenaltyObtainingMethod.valueOf(rs.getString("penalty_obtaining_method"));

            penalties.add(new Penalty(id, player, penalty_time, penalty_result, penalty_obtaining_method));
        }

        return penalties;
    }

    @Override
    public ResponseEntity<List<Penalty>> getPenaltiesByUser(int idUser, int page, int limit) {
        ResponseEntity<List<Penalty>> response;

        try {
            String query = "SELECT * FROM penalties WHERE played_id = ? LIMIT ? OFFSET ?";
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setInt(1, idUser);
            ps.setInt(2, limit);
            ps.setInt(3, (page - 1) * limit);

            ps.executeQuery();

            ResultSet rs = ps.getResultSet();

            List<Penalty> penalties = PenaltyListFromResultSet(rs);

            response = ResponseEntity.ok(penalties);
        } catch (SQLException e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        return response;
    }

    @Override
    public ResponseEntity<List<Penalty>> getPenaltiesByGame(int idGame, int page, int limit) {
        ResponseEntity<List<Penalty>> response;

        try {
            String query = "SELECT * FROM penalties WHERE game_id = ? LIMIT ? OFFSET ?";
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setInt(1, idGame);
            ps.setInt(2, limit);
            ps.setInt(3, (page - 1) * limit);

            ps.executeQuery();

            ResultSet rs = ps.getResultSet();

            List<Penalty> penalties = PenaltyListFromResultSet(rs);

            response = ResponseEntity.ok(penalties);
        } catch (SQLException e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        return response;
    }

    @Override
    public ResponseEntity<List<Penalty>> getPenalties(int page, int limit) {
        ResponseEntity<List<Penalty>> response;

        try {
            String query = "SELECT * FROM penalties LIMIT ? OFFSET ?";
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setInt(1, limit);
            ps.setInt(2, (page - 1) * limit);

            ps.executeQuery();

            ResultSet rs = ps.getResultSet();

            List<Penalty> penalties = PenaltyListFromResultSet(rs);

            response = ResponseEntity.ok(penalties);
        } catch (SQLException e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        return response;
    }

    @Override
    public ResponseEntity<String> updatePenalty(int idPenalty, Map<String, Object> penalty) {
        ResponseEntity<String> response;

        try {
            String query = "UPDATE penalties SET played_id = ?, penalty_time = ?, penalty_result = ?, penalty_obtaining_method = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);

            Time penalty_time = (Time) penalty.get("penalty_time");
            String penalty_result = (String) penalty.get("penalty_result");
            String penalty_obtaining_method = (String) penalty.get("penalty_obtaining_method");

            ps.setInt(1, (int) penalty.get("played_id"));
            ps.setTime(2, penalty_time);
            ps.setString(3, penalty_result);
            ps.setString(4, penalty_obtaining_method);
            ps.setInt(5, idPenalty);

            ps.executeQuery();

            response = ResponseEntity.ok("Penalty updated");
        } catch (SQLException e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        return response;
    }

    @Override
    public ResponseEntity<String> deletePenalty(int idPenalty) {
        ResponseEntity<String> response;

        try {
            String query = "DELETE FROM penalties WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setInt(1, idPenalty);

            ps.executeQuery();

            response = ResponseEntity.ok("Penalty deleted");
        } catch (SQLException e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        return response;
    }

    @Override
    public ResponseEntity<String> removeLastPenalty(int game_id) {
        ResponseEntity<String> response;

        try {
            String query = "DELETE FROM penalties WHERE game_id = ? ORDER BY id DESC LIMIT 1";
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setInt(1, game_id);

            ps.executeQuery();

            response = ResponseEntity.ok("Last penalty removed");
        } catch (SQLException e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        return response;
    }
}
