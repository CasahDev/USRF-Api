package fr.sacha_casahdev.usrf_api.dao.implementation;

import fr.sacha_casahdev.usrf_api.dao.DBConnection;
import fr.sacha_casahdev.usrf_api.dao.interfaces.IMatchDAO;
import fr.sacha_casahdev.usrf_api.models.GameState;
import fr.sacha_casahdev.usrf_api.models.Match;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component("matchDAO")
public class MatchDAO implements IMatchDAO {
    Connection conn;

    public MatchDAO() {
        conn = DBConnection.getConnection();
    }

    @Override
    public ResponseEntity<Match> getMatchById(int id) {
        ResponseEntity<Match> response = null;

        try {
            String query = "SELECT * FROM matches WHERE id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Match match = getMatchFromResultSet(resultSet);
                response = ResponseEntity.ok(match);
            } else {
                response = ResponseEntity.notFound().build();
            }
        } catch (SQLException e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return response;
    }

    @Override
    public ResponseEntity<List<Match>> getMatches(int page, int limit) {
        ResponseEntity<List<Match>> response = null;

        try {
            String query = "SELECT * FROM matches LIMIT ? OFFSET ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, limit);
            preparedStatement.setInt(2, (page - 1) * limit);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Match> matches = MatchListFromResultSet(resultSet);

            response = ResponseEntity.ok(matches);
        } catch (SQLException e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return response;
    }

    private Match getMatchFromResultSet(ResultSet resultSet) throws SQLException {
        Match match = new Match();
        match.setId(resultSet.getInt("id"));
        match.setTeam1(new TeamDAO().getTeamById(resultSet.getInt("team")).getBody());
        match.setTeam2(new TeamDAO().getTeamById(resultSet.getInt("opponent")).getBody());
        match.setScore1(resultSet.getInt("score1"));
        match.setScore2(resultSet.getInt("score2"));
        match.setCup(new CupDAO().getCupById(resultSet.getInt("cup")).getBody());
        match.setDate(resultSet.getTime("date"));
        return match;
    }

    @Override
    public ResponseEntity<Match> addMatch(Match match) {
        ResponseEntity<Match> response = null;

        try {
            String query = "INSERT INTO matches " +
                    "(team1, team2, score1, score2, address, date, is_home, coach, state, started_time, cup) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, match.getTeam1().getId());
            preparedStatement.setInt(2, match.getTeam2().getId());
            preparedStatement.setInt(3, match.getScore1());
            preparedStatement.setInt(4, match.getScore2());
            preparedStatement.setString(5, match.getAddress());
            preparedStatement.setTime(6, match.getDate());
            preparedStatement.setBoolean(7, match.is_home());
            preparedStatement.setString(8, match.getCoach());
            preparedStatement.setString(9, match.getState().name());
            preparedStatement.setTime(10, match.getStarted_time());
            preparedStatement.setInt(11, match.getCup().getId());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                match.setId(id);
                response = ResponseEntity.ok(match);
            } else {
                response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } catch (SQLException e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return response;
    }

    @Override
    public ResponseEntity<Match> updateMatch(int id, Match match) {
        ResponseEntity<Match> response = null;

        try {
            String query = "UPDATE matches SET " +
                    "team1 = ?, team2 = ?, score1 = ?, score2 = ?, address = ?, date = ?, is_home = ?, coach = ?, " +
                    "state = ?, started_time = ?, cup = ? " +
                    "WHERE id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, match.getTeam1().getId());
            preparedStatement.setInt(2, match.getTeam2().getId());
            preparedStatement.setInt(3, match.getScore1());
            preparedStatement.setInt(4, match.getScore2());
            preparedStatement.setString(5, match.getAddress());
            preparedStatement.setTime(6, match.getDate());
            preparedStatement.setBoolean(7, match.is_home());
            preparedStatement.setString(8, match.getCoach());
            preparedStatement.setString(9, match.getState().name());
            preparedStatement.setTime(10, match.getStarted_time());
            preparedStatement.setInt(11, match.getCup().getId());
            preparedStatement.setInt(12, id);
            preparedStatement.executeUpdate();

            response = ResponseEntity.ok(match);
        } catch (SQLException e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return response;
    }

    @Override
    public ResponseEntity<String> deleteMatch(int id) {
        ResponseEntity<String> response = null;

        try {
            String query = "DELETE FROM matches WHERE id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            response = ResponseEntity.ok("Match deleted");
        } catch (SQLException e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return response;
    }

    @Override
    public ResponseEntity<List<Match>> getMatchesByTeam(int teamId, int page, int limit) {
        ResponseEntity<List<Match>> response = null;

        try {
            String query = "SELECT * FROM matches WHERE team1 = ? OR team2 = ? LIMIT ? OFFSET ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, teamId);
            preparedStatement.setInt(2, teamId);
            preparedStatement.setInt(3, limit);
            preparedStatement.setInt(4, (page - 1) * limit);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Match> matches = MatchListFromResultSet(resultSet);

            response = ResponseEntity.ok(matches);
        } catch (SQLException e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return response;
    }

    private List<Match> MatchListFromResultSet(ResultSet resultSet) throws SQLException {
        List<Match> matches = new ArrayList<>();
        while (resultSet.next()) {
            Match match = getMatchFromResultSet(resultSet);
            matches.add(match);
        }

        return matches;
    }

    @Override
    public ResponseEntity<List<Match>> getMatchesCup(int page, int limit) {
        ResponseEntity<List<Match>> response = null;

        try {
            String query = "SELECT * FROM matches " +
                    "INNER JOIN cup on matches.cup = cup.id " +
                    "WHERE cup.name LIKE '%Coupe%' " +
                    "LIMIT ? OFFSET ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, limit);
            preparedStatement.setInt(2, (page - 1) * limit);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Match> matches = MatchListFromResultSet(resultSet);

            response = ResponseEntity.ok(matches);
        } catch (SQLException e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return response;
    }

    @Override
    public ResponseEntity<List<Match>> getMatchesByCup(int cupId, int page, int limit) {
        ResponseEntity<List<Match>> response = null;

        try {
            String query = "SELECT * FROM matches WHERE cup = ? LIMIT ? OFFSET ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, cupId);
            preparedStatement.setInt(2, limit);
            preparedStatement.setInt(3, (page - 1) * limit);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Match> matches = MatchListFromResultSet(resultSet);

            response = ResponseEntity.ok(matches);
        } catch (SQLException e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return response;
    }

    @Override
    public ResponseEntity<List<Match>> getMatchesByTeamAndCup(int teamId, int cupId, int page, int limit) {
        ResponseEntity<List<Match>> response = null;

        try {
            String query = "SELECT * FROM matches WHERE (team1 = ? OR team2 = ?) AND cup = ? LIMIT ? OFFSET ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, teamId);
            preparedStatement.setInt(2, teamId);
            preparedStatement.setInt(3, cupId);
            preparedStatement.setInt(4, limit);
            preparedStatement.setInt(5, (page - 1) * limit);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Match> matches = MatchListFromResultSet(resultSet);

            response = ResponseEntity.ok(matches);
        } catch (SQLException e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return response;
    }

    @Override
    public ResponseEntity<List<Match>> getMatchesByDate(Time date, int page, int limit) {
        ResponseEntity<List<Match>> response = null;

        try {
            String query = "SELECT * FROM matches WHERE date = ? LIMIT ? OFFSET ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setTime(1, date);
            preparedStatement.setInt(2, limit);
            preparedStatement.setInt(3, (page - 1) * limit);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Match> matches = MatchListFromResultSet(resultSet);

            response = ResponseEntity.ok(matches);
        } catch (SQLException e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return response;
    }

    @Override
    public ResponseEntity<List<Match>> getMatchesByDateAndTeam(Time date, int teamId, int page, int limit) {
        ResponseEntity<List<Match>> response = null;

        try {
            String query = "SELECT * FROM matches WHERE (team1 = ? OR team2 = ?) AND date = ? LIMIT ? OFFSET ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, teamId);
            preparedStatement.setInt(2, teamId);
            preparedStatement.setTime(3, date);
            preparedStatement.setInt(4, limit);
            preparedStatement.setInt(5, (page - 1) * limit);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Match> matches = MatchListFromResultSet(resultSet);

            response = ResponseEntity.ok(matches);
        } catch (SQLException e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return response;
    }

    @Override
    public ResponseEntity<List<Match>> getMatchesByDateAndCup(Time date, int cupId, int page, int limit) {
        ResponseEntity<List<Match>> response = null;

        try {
            String query = "SELECT * FROM matches WHERE cup = ? AND date = ? LIMIT ? OFFSET ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, cupId);
            preparedStatement.setTime(2, date);
            preparedStatement.setInt(3, limit);
            preparedStatement.setInt(4, (page - 1) * limit);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Match> matches = MatchListFromResultSet(resultSet);

            response = ResponseEntity.ok(matches);
        } catch (SQLException e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return response;
    }
}
