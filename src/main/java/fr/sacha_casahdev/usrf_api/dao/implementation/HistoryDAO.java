package fr.sacha_casahdev.usrf_api.dao.implementation;

import fr.sacha_casahdev.usrf_api.dao.DBConnection;
import fr.sacha_casahdev.usrf_api.dao.interfaces.IHistoryDAO;
import fr.sacha_casahdev.usrf_api.models.ActionType;
import fr.sacha_casahdev.usrf_api.models.History;
import fr.sacha_casahdev.usrf_api.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component("historyDAO")
public class HistoryDAO implements IHistoryDAO {
    private final Connection conn;

    public HistoryDAO() {
        this.conn = DBConnection.getConnection();
    }

    @Override
    public ResponseEntity<History> createHistory(History history) {
        String query = "INSERT INTO history (author, created_at, action, additional_information) VALUES (?, ?, ?, ?)";

        ResponseEntity<History> response;

        try {
            User author = history.getAuthor();
            Time createdAt = history.getCreated_at();
            ActionType action = history.getAction();
            String additionalInformation = history.getAdditional_information();

            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, author.getId());
            stmt.setTime(2, createdAt);
            stmt.setString(3, (action).name());
            stmt.setString(4, additionalInformation);

            stmt.executeUpdate();

            History newHistory = new History(author, createdAt, action, additionalInformation);

            response = ResponseEntity.status(201).header("message", "History created").body(newHistory);
        } catch (Exception e) {
            response = ResponseEntity.status(500).header("message", "Internal server error", e.getMessage()).body(null);
        }

        return response;
    }

    @Override
    public ResponseEntity<History> getHistory(int id) {
        ResponseEntity<History> response;

        try {
            String query = "SELECT * FROM history WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);

            ResultSet result = stmt.executeQuery();

            List<History> histories = HistoryListFromResultSet(result);

            if (histories.isEmpty()) {
                response = ResponseEntity.status(404).header("message", "History not found").body(null);
            } else {
                response = ResponseEntity.status(200).header("message", "History retrieved").body(histories.get(0));
            }
        } catch (Exception e) {
            response = ResponseEntity.status(500).header("message", "Internal server error", e.getMessage()).
                    body(null);
        }

        return response;
    }

    @Override
    public ResponseEntity<String> deleteHistory(int id) {
        try {
            String query = "DELETE FROM history WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);

            stmt.executeUpdate();

            return ResponseEntity.status(200).header("message", "History deleted").body(null);

        } catch (Exception e) {
            return ResponseEntity.status(500).header("message", "Internal server error", e.getMessage()).body(null);
        }
    }

    @Override
    public ResponseEntity<History> updateHistory(int id, History history) {
        try {
            String query = "UPDATE history SET author = ?, created_at = ?, action = ?, additional_information = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);

            User author = history.getAuthor();
            Time createdAt = history.getCreated_at();
            ActionType action = history.getAction();
            String additionalInformation = history.getAdditional_information();

            stmt.setInt(1, author.getId());
            stmt.setTime(2, createdAt);
            stmt.setString(3, action.name());
            stmt.setString(4, additionalInformation);
            stmt.setInt(5, id);

            stmt.executeUpdate();

            History h = new History();
            h.setId(id);
            h.setAuthor(author);
            h.setCreated_at(createdAt);
            h.setAction(action);

            return ResponseEntity.status(200).header("message", "History updated").body(h);

        } catch (Exception e) {
            return ResponseEntity.status(500).header("message", "Internal server error", e.getMessage()).body(null);
        }
    }

    @Override
    public ResponseEntity<List<History>> getHistories(int page, int limit) {
        try {
            String query = "SELECT * FROM history LIMIT ? OFFSET ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, limit);
            stmt.setInt(2, (page - 1) * limit);

            ResultSet result = stmt.executeQuery();

            List<History> histories = HistoryListFromResultSet(result);

            return ResponseEntity.status(200).header("message", "Histories retrieved").body(histories);
        } catch (Exception e) {
            return ResponseEntity.status(500).header("message", "Internal server error", e.getMessage()).body(null);
        }
    }

    private List<History> HistoryListFromResultSet(ResultSet result) throws SQLException {
        List<History> histories = new ArrayList<>();

        while (result.next()) {
            History history = new History();
            history.setId(result.getInt("id"));
            history.setAuthor(new UserDAO().getUserById(result.getInt("author")).getBody());
            history.setCreated_at(result.getTime("created_at"));
            history.setAction(ActionType.valueOf(result.getString("action")));
            history.setAdditional_information(result.getString("additional_information"));

            histories.add(history);
        }

        return histories;
    }
}
