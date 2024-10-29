package fr.sacha_casahdev.usrf_api.service.implementation;

import fr.sacha_casahdev.usrf_api.dao.interfaces.IMatchHistoryDAO;
import fr.sacha_casahdev.usrf_api.models.MatchHistory;
import fr.sacha_casahdev.usrf_api.service.interfaces.IMatchHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("matchHistoryService")
public class MatchHistoryService implements IMatchHistoryService{
    @Qualifier("matchHistoryDAO")
    private final IMatchHistoryDAO dao;

    @Autowired
    public MatchHistoryService(IMatchHistoryDAO dao) {
        this.dao = dao;
    }


    @Override
    public ResponseEntity<MatchHistory> getMatchHistoryById(int id) {
        return dao.getMatchHistoryById(id);
    }

    @Override
    public ResponseEntity<List<MatchHistory>> getMatchHistoriesByMatchId(int matchId, int page, int limit) {
        return dao.getMatchHistoriesByMatchId(matchId, page, limit);
    }

    @Override
    public ResponseEntity<MatchHistory> createMatchHistory(MatchHistory matchHistory) {
        return dao.createMatchHistory(matchHistory);
    }

    @Override
    public ResponseEntity<MatchHistory> updateMatchHistory(int id, MatchHistory matchHistory) {
        return dao.updateMatchHistory(id, matchHistory);
    }

    @Override
    public ResponseEntity<String> deleteMatchHistory(int id) {
        return dao.deleteMatchHistory(id);
    }

    @Override
    public ResponseEntity<List<MatchHistory>> getMatchHistories(int page, int limit) {
        return dao.getMatchHistories(page, limit);
    }

    @Override
    public ResponseEntity<List<MatchHistory>> getMatchHistoriesByEvent(String event, int page, int limit) {
        return dao.getMatchHistoriesByEvent(event, page, limit);
    }

    @Override
    public ResponseEntity<List<MatchHistory>> getMatchHistoriesByMatchIdAndEvent(int matchId, String event, int page, int limit) {
        return dao.getMatchHistoriesByMatchIdAndEvent(matchId, event, page, limit);
    }

    @Override
    public ResponseEntity<MatchHistory> getLastMatchHistory(int matchId) {
        return dao.getLastMatchHistory(matchId);
    }
}
