package fr.sacha_casahdev.usrf_api.dao.interfaces;

import fr.sacha_casahdev.usrf_api.models.Played;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface IPlayedDAO {
    ResponseEntity<List<Played>> getPlayedByMatch(int match_id);
    ResponseEntity<List<Played>> getPlayedByPlayer(int player_id);
    ResponseEntity<List<Played>> getPlayedByTeam(int team_id);
    ResponseEntity<List<Played>> getPlayedByCup(int cup_id);
    ResponseEntity<List<Played>> getPlayedByMatchAndTeam(int match_id, int team_id);
    ResponseEntity<List<Played>> getPlayedByPlayerAndCup(int player_id, int cup_id);
    ResponseEntity<List<Played>> getPlayedByTeamAndCup(int team_id, int cup_id);
    ResponseEntity<List<Played>> getPlayedByPlayerAndTeamAndCup(int player_id, int team_id, int cup_id);
    ResponseEntity<Played> getPlayed(int id);
    ResponseEntity<Played> createPlayed(Map<String, Object> played);
    ResponseEntity<Played> updatePlayed(Map<String, Object> played);
    ResponseEntity<String> deletePlayed(int id);
}
