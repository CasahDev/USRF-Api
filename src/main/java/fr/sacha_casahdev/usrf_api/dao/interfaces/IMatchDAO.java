package fr.sacha_casahdev.usrf_api.dao.interfaces;

import fr.sacha_casahdev.usrf_api.models.Match;
import org.springframework.http.ResponseEntity;

import java.sql.Time;
import java.util.List;

public interface IMatchDAO {
    ResponseEntity<Match> getMatchById(int id);
    ResponseEntity<List<Match>> getMatches(int page, int limit);
    ResponseEntity<Match> addMatch(Match match);
    ResponseEntity<Match> updateMatch(int id, Match match);
    ResponseEntity<String> deleteMatch(int id);
    ResponseEntity<List<Match>> getMatchesByTeam(int teamId, int page, int limit);
    ResponseEntity<List<Match>> getMatchesCup(int page, int limit);
    ResponseEntity<List<Match>> getMatchesByCup(int cupId, int page, int limit);
    ResponseEntity<List<Match>> getMatchesByTeamAndCup(int teamId, int cupId, int page, int limit);
    ResponseEntity<List<Match>> getMatchesByDate(Time date, int page, int limit);
    ResponseEntity<List<Match>> getMatchesByDateAndTeam(Time date, int teamId, int page, int limit);
    ResponseEntity<List<Match>> getMatchesByDateAndCup(Time date, int cupId, int page, int limit);
}
