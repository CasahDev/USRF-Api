package fr.sacha_casahdev.usrf_api.dao.implementation;

import fr.sacha_casahdev.usrf_api.dao.DBConnection;
import fr.sacha_casahdev.usrf_api.dao.interfaces.IHistoryDAO;
import fr.sacha_casahdev.usrf_api.models.ActionType;
import fr.sacha_casahdev.usrf_api.models.History;
import fr.sacha_casahdev.usrf_api.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.Map;

@Component("historyDAO")
public class HistoryDAO implements IHistoryDAO {
    private Connection conn;

    public HistoryDAO() {
        this.conn = DBConnection.getConnection();
    }

    @Override
    public ResponseEntity<History> createHistory(Map<String, Object> history) {
        String query = "INSERT INTO history (author, created_at, action, additional_information) VALUES (?, ?, ?, ?)";

        ResponseEntity<History> response;

        try {
            User author = (User) history.get("author");
            Time createdAt = (Time) history.get("created_at");
            ActionType action = (ActionType) history.get("action");
            String additionalInformation = (String) history.get("add²itional_information");

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

            History history = new History();

            if (result.next()) {
                history.setId(result.getInt("id"));
                history.setAuthor(new UserDAO().getUserById(result.getInt("author")).getBody());
                history.setCreated_at(result.getTime("created_at"));
                history.setAction(ActionType.valueOf(result.getString("action")));
                history.setAdditional_information(result.getString("additional_information"));
            }

            response = ResponseEntity.status(200).header("message", "History retrieved").body(history);

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
    public ResponseEntity<History> updateHistory(int id, Map<String, Object> history) {
        try {
            String query = "UPDATE history SET author = ?, created_at = ?, action = ?, additional_information = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);

            User author = (User) history.get("author");
            Time createdAt = (Time) history.get("created_at");
            ActionType action = (ActionType) history.get("action");
            String additionalInformation = (String) history.get("additional_information");

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
    public ResponseEntity<String> getHistories(int page, int limit) {
        try {
            String query = "SELECT * FROM history LIMIT ? OFFSET ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, limit);
            stmt.setInt(2, (page - 1) * limit);

            ResultSet result = stmt.executeQuery();

            StringBuilder histories = new StringBuilder();

            while (result.next()) {
                histories.append("History{id=").append(result.getInt("id")).append(", author=").append(new UserDAO().getUserById(result.getInt("author")).getBody().toString()).append(", created_at=").append(result.getTime("created_at")).append(", action=").append(ActionType.valueOf(result.getString("action"))).append(", additional_information='").append(result.getString("additional_information")).append("'}\n");
            }

            return ResponseEntity.status(200).header("message", "Histories retrieved").body(histories.toString());
        } catch (Exception e) {
            return ResponseEntity.status(500).header("message", "Internal server error", e.getMessage()).body(null);
        }
    }
}
