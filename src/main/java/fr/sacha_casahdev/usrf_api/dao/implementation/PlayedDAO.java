package fr.sacha_casahdev.usrf_api.dao.implementation;

import fr.sacha_casahdev.usrf_api.dao.DBConnection;
import fr.sacha_casahdev.usrf_api.dao.interfaces.IPlayedDAO;
import fr.sacha_casahdev.usrf_api.models.Played;
import fr.sacha_casahdev.usrf_api.models.Positions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component("playedDAO")
public class PlayedDAO implements IPlayedDAO {
    Connection conn;

    public PlayedDAO() {
        this.conn = DBConnection.getConnection();
    }

    @Override
    public ResponseEntity<List<Played>> getPlayedByMatch(int match_id) {
        ResponseEntity<List<Played>> response = null;

        try {
            String query = "SELECT * FROM played WHERE match_id = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, match_id);
            ResultSet result = statement.executeQuery();

            List<Played> playedList = PlayedListFromResultSet(result);

            response = ResponseEntity.ok(playedList);
        } catch (SQLException e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        return response;
    }

    private List<Played> PlayedListFromResultSet(ResultSet result) throws SQLException {
        List<Played> playedList = new ArrayList<>();
        while (result.next()) {
            Played played = new Played();
            played.setId(result.getInt("id"));
            played.setPlayer(new PlayerDAO().getPlayerById(result.getInt("player_id")).getBody());
            played.setMatch(new MatchDAO().getMatchById(result.getInt("match_id")).getBody());
            played.set_injured(result.getBoolean("injured"));
            played.set_capitain(result.getBoolean("capitain"));
            played.set_injured(result.getBoolean("injured"));
            played.setBlocked_shots(result.getInt("blocked_shots"));
            played.setGoals(result.getInt("goals"));
            played.setEntry_time(result.getTime("entry_time"));
            played.setExit_time(result.getTime("exit_time"));
            played.setYellow_card(result.getBoolean("yellow_card"));
            played.setRed_card(result.getBoolean("red_card"));
            played.setJersey_number(result.getInt("jersey_number"));
            played.setOff_target_shots(result.getInt("off_target_shots"));
            played.setOn_target_shots(result.getInt("on_target_shots"));
            played.setPosition(Positions.valueOf(result.getString("position")));

            playedList.add(played);
        }

        return playedList;
    }

    @Override
    public ResponseEntity<List<Played>> getPlayedByPlayer(int player_id) {
        ResponseEntity<List<Played>> response = null;

        try {
            String query = "SELECT * FROM played WHERE player_id = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, player_id);
            ResultSet result = statement.executeQuery();

            List<Played> playedList = PlayedListFromResultSet(result);

            response = ResponseEntity.ok(playedList);
        } catch (SQLException e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        return response;
    }

    @Override
    public ResponseEntity<List<Played>> getPlayedByTeam(int team_id) {
        ResponseEntity<List<Played>> response = null;

        try {
            String query = "SELECT * FROM played WHERE team_id = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, team_id);
            ResultSet result = statement.executeQuery();

            List<Played> playedList = PlayedListFromResultSet(result);

            response = ResponseEntity.ok(playedList);
        } catch (SQLException e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        return response;
    }

    @Override
    public ResponseEntity<List<Played>> getPlayedByCup(int cup_id) {
        ResponseEntity<List<Played>> response = null;

        try {
            String query = "SELECT * FROM played INNER JOIN matches on played.match_id = matches.id WHERE matches.cup = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, cup_id);
            ResultSet result = statement.executeQuery();

            List<Played> playedList = PlayedListFromResultSet(result);

            response = ResponseEntity.ok(playedList);
        } catch (SQLException e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        return response;
    }

    @Override
    public ResponseEntity<List<Played>> getPlayedByMatchAndTeam(int match_id, int team_id) {
        ResponseEntity<List<Played>> response = null;

        try {
            String query = "SELECT * FROM played WHERE match_id = ? AND team_id = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, match_id);
            statement.setInt(2, team_id);
            ResultSet result = statement.executeQuery();

            List<Played> playedList = PlayedListFromResultSet(result);

            response = ResponseEntity.ok(playedList);
        } catch (SQLException e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        return response;
    }

    @Override
    public ResponseEntity<List<Played>> getPlayedByPlayerAndCup(int player_id, int cup_id) {
        ResponseEntity<List<Played>> response = null;

        try {
            String query = "SELECT * FROM played INNER JOIN matches on played.match_id = matches.id WHERE player_id = ? AND matches.cup = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, player_id);
            statement.setInt(2, cup_id);
            ResultSet result = statement.executeQuery();

            List<Played> playedList = PlayedListFromResultSet(result);

            response = ResponseEntity.ok(playedList);
        } catch (SQLException e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        return response;
    }

    @Override
    public ResponseEntity<List<Played>> getPlayedByTeamAndCup(int team_id, int cup_id) {
        ResponseEntity<List<Played>> response = null;

        try {
            String query = "SELECT * FROM played INNER JOIN matches on played.match_id = matches.id WHERE team_id = ? AND matches.cup = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, team_id);
            statement.setInt(2, cup_id);
            ResultSet result = statement.executeQuery();

            List<Played> playedList = PlayedListFromResultSet(result);

            response = ResponseEntity.ok(playedList);
        } catch (SQLException e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        return response;
    }

    @Override
    public ResponseEntity<List<Played>> getPlayedByPlayerAndTeamAndCup(int player_id, int team_id, int cup_id) {
        ResponseEntity<List<Played>> response = null;

        try {
            String query = "SELECT * FROM played INNER JOIN matches on played.match_id = matches.id WHERE player_id = ? AND team_id = ? AND matches.cup = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, player_id);
            statement.setInt(2, team_id);
            statement.setInt(3, cup_id);
            ResultSet result = statement.executeQuery();

            List<Played> playedList = PlayedListFromResultSet(result);

            response = ResponseEntity.ok(playedList);
        } catch (SQLException e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        return response;
    }

    @Override
    public ResponseEntity<Played> getPlayed(int id) {
        ResponseEntity<Played> response = null;

        try {
            String query = "SELECT * FROM played WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();

            List<Played> playedList = PlayedListFromResultSet(result);

            if (!playedList.isEmpty()) {
                response = ResponseEntity.ok(playedList.get(0));
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (SQLException e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        return response;
    }

    @Override
    public ResponseEntity<Played> createPlayed(Map<String, Object> played) {
        ResponseEntity<Played> response = null;

        try {
            String query = "INSERT INTO played " +
                    "(player_id, match_id, injured, capitain, blocked_shots, goals, entry_time, exit_time, " +
                    "yellow_card, red_card, jersey_number, off_target_shots, on_target_shots, position) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, (int) played.get("player_id"));
            statement.setInt(2, (int) played.get("match_id"));
            statement.setBoolean(3, (boolean) played.get("injured"));
            statement.setBoolean(4, (boolean) played.get("capitain"));
            statement.setInt(5, (int) played.get("blocked_shots"));
            statement.setInt(6, (int) played.get("goals"));
            statement.setTime(7, (Time) played.get("entry_time"));
            statement.setTime(8, (Time) played.get("exit_time"));
            statement.setBoolean(9, (boolean) played.get("yellow_card"));
            statement.setBoolean(10, (boolean) played.get("red_card"));
            statement.setInt(11, (int) played.get("jersey_number"));
            statement.setInt(12, (int) played.get("off_target_shots"));
            statement.setInt(13, (int) played.get("on_target_shots"));
            statement.setString(14, ((Positions) played.get("position")).name());

            statement.executeUpdate();

            Played p = new Played();
            p.fromJson(played);

            response = ResponseEntity.ok(p);

        } catch (Exception e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        return response;
    }

    @Override
    public ResponseEntity<Played> updatePlayed(Map<String, Object> played) {
        ResponseEntity<Played> response = null;

        try {
            String query = "UPDATE played SET " +
                    "player_id = ?, match_id = ?, injured = ?, capitain = ?, blocked_shots = ?, goals = ?, entry_time = ?, exit_time = ?, " +
                    "yellow_card = ?, red_card = ?, jersey_number = ?, off_target_shots = ?, on_target_shots = ?, position = ? " +
                    "WHERE id = ?";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, (int) played.get("player_id"));
            statement.setInt(2, (int) played.get("match_id"));
            statement.setBoolean(3, (boolean) played.get("injured"));
            statement.setBoolean(4, (boolean) played.get("capitain"));
            statement.setInt(5, (int) played.get("blocked_shots"));
            statement.setInt(6, (int) played.get("goals"));
            statement.setTime(7, (Time) played.get("entry_time"));
            statement.setTime(8, (Time) played.get("exit_time"));
            statement.setBoolean(9, (boolean) played.get("yellow_card"));
            statement.setBoolean(10, (boolean) played.get("red_card"));
            statement.setInt(11, (int) played.get("jersey_number"));
            statement.setInt(12, (int) played.get("off_target_shots"));
            statement.setInt(13, (int) played.get("on_target_shots"));
            statement.setString(14, ((Positions) played.get("position")).name());
            statement.setInt(15, (int) played.get("id"));

            statement.executeUpdate();

            Played p = new Played();
            p.fromJson(played);

            response = ResponseEntity.ok(p);
        } catch (Exception e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        return response;
    }

    @Override
    public ResponseEntity<String> deletePlayed(int id) {
        ResponseEntity<String> response = null;

        try {
            String query = "DELETE FROM played WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();

            response = ResponseEntity.ok("Played deleted");
        } catch (SQLException e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        return response;
    }

    @Override
    public ResponseEntity<Played> getPlayedByPlayerAndMatch(int player_id, int match_id) {
        ResponseEntity<Played> response = null;

        try {
            String query = "SELECT * FROM played WHERE player_id = ? AND match_id = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, player_id);
            statement.setInt(2, match_id);
            ResultSet result = statement.executeQuery();

            List<Played> playedList = PlayedListFromResultSet(result);

            if (!playedList.isEmpty()) {
                response = ResponseEntity.ok(playedList.get(0));
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (SQLException e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        return response;
    }
}
