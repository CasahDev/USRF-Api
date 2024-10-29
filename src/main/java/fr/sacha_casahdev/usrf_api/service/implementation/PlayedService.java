package fr.sacha_casahdev.usrf_api.service.implementation;

import fr.sacha_casahdev.usrf_api.dao.interfaces.IPlayedDAO;
import fr.sacha_casahdev.usrf_api.models.Played;
import fr.sacha_casahdev.usrf_api.service.interfaces.IPlayedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component("playedService")
public class PlayedService implements IPlayedService {
    @Qualifier("playedDAO")
    private IPlayedDAO dao;

    @Autowired
    public PlayedService(IPlayedDAO dao) {
        this.dao = dao;
    }

    @Override
    public ResponseEntity<List<Played>> getPlayedByMatch(int match_id) {
        return dao.getPlayedByMatch(match_id);
    }

    @Override
    public ResponseEntity<List<Played>> getPlayedByPlayer(int player_id) {
        return dao.getPlayedByPlayer(player_id);
    }

    @Override
    public ResponseEntity<List<Played>> getPlayedByTeam(int team_id) {
        return dao.getPlayedByTeam(team_id);
    }

    @Override
    public ResponseEntity<List<Played>> getPlayedByCup(int cup_id) {
        return dao.getPlayedByCup(cup_id);
    }

    @Override
    public ResponseEntity<List<Played>> getPlayedByMatchAndTeam(int match_id, int team_id) {
        return dao.getPlayedByMatchAndTeam(match_id, team_id);
    }

    @Override
    public ResponseEntity<List<Played>> getPlayedByPlayerAndCup(int player_id, int cup_id) {
        return dao.getPlayedByPlayerAndCup(player_id, cup_id);
    }

    @Override
    public ResponseEntity<List<Played>> getPlayedByTeamAndCup(int team_id, int cup_id) {
        return dao.getPlayedByTeamAndCup(team_id, cup_id);
    }

    @Override
    public ResponseEntity<List<Played>> getPlayedByPlayerAndTeamAndCup(int player_id, int team_id, int cup_id) {
        return dao.getPlayedByPlayerAndTeamAndCup(player_id, team_id, cup_id);
    }

    @Override
    public ResponseEntity<Played> getPlayed(int id) {
        return dao.getPlayed(id);
    }

    @Override
    public ResponseEntity<Played> createPlayed(Map<String, Object> played) {
        return dao.createPlayed(played);
    }

    @Override
    public ResponseEntity<Played> updatePlayed(Map<String, Object> played) {
        return dao.updatePlayed(played);
    }

    @Override
    public ResponseEntity<String> deletePlayed(int id) {
        return dao.deletePlayed(id);
    }

    @Override
    public ResponseEntity<Played> getPlayedByPlayerAndMatch(int player_id, int match_id) {
        return dao.getPlayedByPlayerAndMatch(player_id, match_id);
    }
}
