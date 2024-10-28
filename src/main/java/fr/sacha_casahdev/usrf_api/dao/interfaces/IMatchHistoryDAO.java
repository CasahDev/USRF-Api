package fr.sacha_casahdev.usrf_api.dao.interfaces;

import fr.sacha_casahdev.usrf_api.models.MatchHistory;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface IMatchHistoryDAO {
    ResponseEntity<MatchHistory> getMatchHistoryById(int id);
    ResponseEntity<List<MatchHistory>> getMatchHistoriesByMatchId(int matchId, int page, int limit);
    ResponseEntity<MatchHistory> createMatchHistory(Map<String, Object> matchHistory);
    ResponseEntity<MatchHistory> updateMatchHistory(int id, Map<String, Object> matchHistory);
    ResponseEntity<String> deleteMatchHistory(int id);
    ResponseEntity<List<MatchHistory>> getMatchHistories(int page, int limit);
    ResponseEntity<List<MatchHistory>> getMatchHistoriesByEvent(String event, int page, int limit);
    ResponseEntity<List<MatchHistory>> getMatchHistoriesByMatchIdAndEvent(int matchId, String event, int page, int limit);
    ResponseEntity<MatchHistory> getLastMatchHistory(int matchId);
}
