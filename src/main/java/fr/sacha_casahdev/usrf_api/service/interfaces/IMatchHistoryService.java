package fr.sacha_casahdev.usrf_api.service.interfaces;

import fr.sacha_casahdev.usrf_api.models.MatchHistory;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IMatchHistoryService {
    ResponseEntity<MatchHistory> getMatchHistoryById(int id);
    ResponseEntity<List<MatchHistory>> getMatchHistoriesByMatchId(int matchId, int page, int limit);
    ResponseEntity<MatchHistory> createMatchHistory(MatchHistory matchHistory);
    ResponseEntity<MatchHistory> updateMatchHistory(int id, MatchHistory matchHistory);
    ResponseEntity<String> deleteMatchHistory(int id);
    ResponseEntity<List<MatchHistory>> getMatchHistories(int page, int limit);
    ResponseEntity<List<MatchHistory>> getMatchHistoriesByEvent(String event, int page, int limit);
    ResponseEntity<List<MatchHistory>> getMatchHistoriesByMatchIdAndEvent(int matchId, String event, int page, int limit);
    ResponseEntity<MatchHistory> getLastMatchHistory(int matchId);
}
