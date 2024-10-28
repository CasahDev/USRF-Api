package fr.sacha_casahdev.usrf_api.dao.implementation;

import fr.sacha_casahdev.usrf_api.dao.DBConnection;
import fr.sacha_casahdev.usrf_api.dao.interfaces.ICanPlayDAO;
import fr.sacha_casahdev.usrf_api.models.CanPlay;
import fr.sacha_casahdev.usrf_api.models.Positions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component("canPlayDAO")
public class CanPlayDAO implements ICanPlayDAO {

    Connection conn;

    public CanPlayDAO() {
        this.conn = DBConnection.getConnection();
    }

    @Override
    public ResponseEntity<CanPlay> getCanPlayById(int id) {
        ResponseEntity<CanPlay> response;

        try {
            String query = "SELECT * FROM can_play WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                CanPlay canPlay = new CanPlay();
                canPlay.setId(rs.getInt("id"));
                canPlay.setPlayer(new PlayerDAO().getPlayerById(rs.getInt("player_id")).getBody());
                canPlay.setPosition(Positions.valueOf(rs.getString("position")));
                response = ResponseEntity.ok(canPlay);
            } else {
                response = ResponseEntity.notFound().build();
            }
        } catch (SQLException e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return response;
    }

    @Override
    public ResponseEntity<List<CanPlay>> getCanPlays(int page, int limit) {
        ResponseEntity<List<CanPlay>> response;

        try {
            String query = "SELECT * FROM can_play LIMIT ? OFFSET ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, limit);
            stmt.setInt(2, (page - 1) * limit);
            ResultSet rs = stmt.executeQuery();

            List<CanPlay> canPlays = ResultSetToCanPlayList(rs);
            response = ResponseEntity.ok(canPlays);
        } catch (SQLException e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return response;
    }

    @Override
    public ResponseEntity<CanPlay> addCanPlay(int player_id, String position) {
        ResponseEntity<CanPlay> response;

        try {
            String query = "INSERT INTO can_play (player_id, position) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, player_id);
            stmt.setString(2, position);
            stmt.executeUpdate();

            int id = stmt.getGeneratedKeys().getInt(1);

            CanPlay canPlay = new CanPlay();
            canPlay.setId(id);
            canPlay.setPlayer(new PlayerDAO().getPlayerById(player_id).getBody());
            canPlay.setPosition(Positions.valueOf(position));

            response = ResponseEntity.ok(canPlay);
        } catch (SQLException e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return response;
    }

    @Override
    public ResponseEntity<CanPlay> updateCanPlay(int id, String position) {
        ResponseEntity<CanPlay> response = null;

        try {
            String query = "UPDATE can_play SET position = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, position);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            response = ResponseEntity.ok().build();
        } catch (SQLException e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return response;
    }

    @Override
    public ResponseEntity<String> deleteCanPlay(int id) {
        ResponseEntity<String> response;

        try {
            String query = "DELETE FROM can_play WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            response = ResponseEntity.ok().build();
        } catch (SQLException e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return response;
    }

    @Override
    public ResponseEntity<List<CanPlay>> getCanPlaysByPlayer(int player_id) {
        ResponseEntity<List<CanPlay>> response;

        try {
            String query = "SELECT * FROM can_play WHERE player_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, player_id);
            ResultSet rs = stmt.executeQuery();

            List<CanPlay> canPlays = ResultSetToCanPlayList(rs);
            response = ResponseEntity.ok(canPlays);
        } catch (SQLException e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return response;
    }

    public ResponseEntity<List<CanPlay>> getCanPlaysByPosition(String position) {
        ResponseEntity<List<CanPlay>> response;

        try {
            String query = "SELECT * FROM can_play WHERE position = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, position);
            ResultSet rs = stmt.executeQuery();

            List<CanPlay> canPlays = ResultSetToCanPlayList(rs);
            response = ResponseEntity.ok(canPlays);
        } catch (SQLException e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return response;
    }

    private List<CanPlay> ResultSetToCanPlayList(ResultSet rs) throws SQLException {
        List<CanPlay> canPlays = new ArrayList<>();
        while (rs.next()) {
            CanPlay canPlay = new CanPlay();
            canPlay.setId(rs.getInt("id"));
            canPlay.setPlayer(new PlayerDAO().getPlayerById(rs.getInt("player_id")).getBody());
            canPlay.setPosition(Positions.valueOf(rs.getString("position")));
            canPlays.add(canPlay);
        }
        return canPlays;
    }
}
