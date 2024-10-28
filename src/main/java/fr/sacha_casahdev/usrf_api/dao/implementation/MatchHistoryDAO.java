package fr.sacha_casahdev.usrf_api.dao.implementation;

import fr.sacha_casahdev.usrf_api.dao.DBConnection;
import fr.sacha_casahdev.usrf_api.dao.interfaces.IMatchHistoryDAO;
import fr.sacha_casahdev.usrf_api.models.Match;
import fr.sacha_casahdev.usrf_api.models.MatchEvent;
import fr.sacha_casahdev.usrf_api.models.MatchHistory;
import org.springframework.http.ResponseEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MatchHistoryDAO implements IMatchHistoryDAO {
    Connection conn;

    public MatchHistoryDAO() {
        conn = DBConnection.getConnection();
    }

    @Override
    public ResponseEntity<MatchHistory> getMatchHistoryById(int id) {
        ResponseEntity<MatchHistory> response;

        try {
            String query = "SELECT * FROM match_histories WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            rs.next();

            Match match = new MatchDAO().getMatchById(rs.getInt("match_id")).getBody();

            MatchHistory matchHistory = new MatchHistory(
                    id,
                    match,
                    MatchEvent.valueOf(rs.getString("event")),
                    (Time) rs.getObject("time"),
                    rs.getString("additionnal_informations")
            );

            response = ResponseEntity.ok(matchHistory);
        }
        catch (Exception e) {
            response = ResponseEntity.notFound().build();
        }

        return response;
    }

    @Override
    public ResponseEntity<List<MatchHistory>> getMatchHistoriesByMatchId(int matchId, int page, int limit) {
        ResponseEntity<List<MatchHistory>> response;

        try {
            String query = "SELECT * FROM match_histories WHERE match_id = ? LIMIT ? OFFSET ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, matchId);
            stmt.setInt(2, limit);
            stmt.setInt(3, (page - 1) * limit);

            ResultSet rs = stmt.executeQuery();
            List<MatchHistory> matchHistories = MatchHistoryListFromResultSet(rs);

            response = ResponseEntity.ok(matchHistories);
        }
        catch (Exception e) {
            response = ResponseEntity.notFound().build();
        }

        return response;
    }

    private List<MatchHistory> MatchHistoryListFromResultSet(ResultSet rs) throws SQLException {
        List<MatchHistory> matchHistories = new ArrayList<>();

        while (rs.next()) {
            Match match = new MatchDAO().getMatchById(rs.getInt("match_id")).getBody();

            MatchHistory matchHistory = new MatchHistory(rs.getInt("id"), match, MatchEvent.valueOf(rs.getString("event")), (Time) rs.getObject("time"), rs.getString("additionnal_informations"));
            matchHistories.add(matchHistory);
        }

        return matchHistories;
    }

    @Override
    public ResponseEntity<MatchHistory> createMatchHistory(Map<String, Object> matchHistory) {
        ResponseEntity<MatchHistory> response;

        try {
            String query = "INSERT INTO match_histories (match_id, event, time, additionnal_informations) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, (int) matchHistory.get("match_id"));
            stmt.setString(2, (String) matchHistory.get("event"));
            stmt.setTime(3, (Time) matchHistory.get("time"));
            stmt.setString(4, (String) matchHistory.get("additionnal_informations"));

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();

            Match match = new MatchDAO().getMatchById((int) matchHistory.get("match_id")).getBody();

            MatchHistory newMatchHistory = new MatchHistory(
                    rs.getInt(1),
                    match,
                    MatchEvent.valueOf((String) matchHistory.get("event")),
                    (Time) matchHistory.get("time"),
                    (String) matchHistory.get("additionnal_informations")
            );

            response = ResponseEntity.ok(newMatchHistory);
        }
        catch (Exception e) {
            response = ResponseEntity.badRequest().build();
        }

        return response;
    }

    @Override
    public ResponseEntity<MatchHistory> updateMatchHistory(int id, Map<String, Object> matchHistory) {
        ResponseEntity<MatchHistory> response;

        try {
            String query = "UPDATE match_histories SET match_id = ?, event = ?, time = ?, additionnal_informations = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, (int) matchHistory.get("match_id"));
            stmt.setString(2, (String) matchHistory.get("event"));
            stmt.setTime(3, (Time) matchHistory.get("time"));
            stmt.setString(4, (String) matchHistory.get("additionnal_informations"));
            stmt.setInt(5, id);

            stmt.executeUpdate();

            Match match = new MatchDAO().getMatchById((int) matchHistory.get("match_id")).getBody();

            MatchHistory updatedMatchHistory = new MatchHistory(
                    id,
                    match,
                    MatchEvent.valueOf((String) matchHistory.get("event")),
                    (Time) matchHistory.get("time"),
                    (String) matchHistory.get("additionnal_informations")
            );

            response = ResponseEntity.ok(updatedMatchHistory);
        }
        catch (Exception e) {
            response = ResponseEntity.badRequest().build();
        }

        return response;
    }

    @Override
    public ResponseEntity<String> deleteMatchHistory(int id) {
        ResponseEntity<String> response;

        try {
            String query = "DELETE FROM match_histories WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);

            stmt.executeUpdate();

            response = ResponseEntity.ok("Match history deleted successfully");
        }
        catch (Exception e) {
            response = ResponseEntity.badRequest().build();
        }

        return response;
    }

    @Override
    public ResponseEntity<List<MatchHistory>> getMatchHistories(int page, int limit) {
        ResponseEntity<List<MatchHistory>> response;

        try {
            String query = "SELECT * FROM match_histories LIMIT ? OFFSET ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, limit);
            stmt.setInt(2, (page - 1) * limit);

            ResultSet rs = stmt.executeQuery();
            List<MatchHistory> matchHistories = MatchHistoryListFromResultSet(rs);

            response = ResponseEntity.ok(matchHistories);
        }
        catch (Exception e) {
            response = ResponseEntity.notFound().build();
        }

        return response;
    }

    @Override
    public ResponseEntity<List<MatchHistory>> getMatchHistoriesByEvent(String event, int page, int limit) {
        ResponseEntity<List<MatchHistory>> response;

        try {
            String query = "SELECT * FROM match_histories WHERE event = ? LIMIT ? OFFSET ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, event);
            stmt.setInt(2, limit);
            stmt.setInt(3, (page - 1) * limit);

            ResultSet rs = stmt.executeQuery();
            List<MatchHistory> matchHistories = MatchHistoryListFromResultSet(rs);

            response = ResponseEntity.ok(matchHistories);
        }
        catch (Exception e) {
            response = ResponseEntity.notFound().build();
        }

        return response;
    }

    @Override
    public ResponseEntity<List<MatchHistory>> getMatchHistoriesByMatchIdAndEvent(int matchId, String event, int page, int limit) {
        ResponseEntity<List<MatchHistory>> response;

        try {
            String query = "SELECT * FROM match_histories WHERE match_id = ? AND event = ? LIMIT ? OFFSET ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, matchId);
            stmt.setString(2, event);
            stmt.setInt(3, limit);
            stmt.setInt(4, (page - 1) * limit);

            ResultSet rs = stmt.executeQuery();
            List<MatchHistory> matchHistories = MatchHistoryListFromResultSet(rs);

            response = ResponseEntity.ok(matchHistories);
        }
        catch (Exception e) {
            response = ResponseEntity.notFound().build();
        }

        return response;
    }

    @Override
    public ResponseEntity<MatchHistory> getLastMatchHistory(int matchId) {
        ResponseEntity<MatchHistory> response;

        try {
            String query = "SELECT * FROM match_histories WHERE match_id = ? ORDER BY id DESC LIMIT 1";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, matchId);

            ResultSet rs = stmt.executeQuery();
            rs.next();

            Match match = new MatchDAO().getMatchById(rs.getInt("match_id")).getBody();

            MatchHistory matchHistory = new MatchHistory(
                    rs.getInt("id"),
                    match,
                    MatchEvent.valueOf(rs.getString("event")),
                    (Time) rs.getObject("time"),
                    rs.getString("additionnal_informations")
            );

            response = ResponseEntity.ok(matchHistory);
        }
        catch (Exception e) {
            response = ResponseEntity.notFound().build();
        }

        return response;
    }
}
