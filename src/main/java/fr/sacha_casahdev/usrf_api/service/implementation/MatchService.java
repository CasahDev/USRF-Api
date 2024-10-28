package fr.sacha_casahdev.usrf_api.service.implementation;

import fr.sacha_casahdev.usrf_api.dao.interfaces.IMatchDAO;
import fr.sacha_casahdev.usrf_api.models.Match;
import fr.sacha_casahdev.usrf_api.service.interfaces.IMatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.util.List;
import java.util.Map;

@Component("matchService")
public class MatchService implements IMatchService {
    @Qualifier("matchDAO")
    private final IMatchDAO dao;

    @Autowired
    public MatchService(IMatchDAO dao) {
        this.dao = dao;
    }

    @Override
    public ResponseEntity<Match> getMatchById(int id) {
        return dao.getMatchById(id);
    }

    @Override
    public ResponseEntity<List<Match>> getMatches(int page, int limit) {
        return dao.getMatches(page, limit);
    }

    @Override
    public ResponseEntity<Match> addMatch(Map<String, Object> match) {
        return dao.addMatch(match);
    }

    @Override
    public ResponseEntity<Match> updateMatch(int id, Map<String, Object> match) {
        return dao.updateMatch(id, match);
    }

    @Override
    public ResponseEntity<String> deleteMatch(int id) {
        return dao.deleteMatch(id);
    }

    @Override
    public ResponseEntity<List<Match>> getMatchesByTeam(int teamId, int page, int limit) {
        return dao.getMatchesByTeam(teamId, page, limit);
    }

    @Override
    public ResponseEntity<List<Match>> getMatchesCup(int page, int limit) {
        return dao.getMatchesCup(page, limit);
    }

    @Override
    public ResponseEntity<List<Match>> getMatchesByCup(int cupId, int page, int limit) {
        return dao.getMatchesByCup(cupId, page, limit);
    }

    @Override
    public ResponseEntity<List<Match>> getMatchesByTeamAndCup(int teamId, int cupId, int page, int limit) {
        return dao.getMatchesByTeamAndCup(teamId, cupId, page, limit);
    }

    @Override
    public ResponseEntity<List<Match>> getMatchesByDate(Time date, int page, int limit) {
        return dao.getMatchesByDate(date, page, limit);
    }

    @Override
    public ResponseEntity<List<Match>> getMatchesByDateAndTeam(Time date, int teamId, int page, int limit) {
        return dao.getMatchesByDateAndTeam(date, teamId, page, limit);
    }

    @Override
    public ResponseEntity<List<Match>> getMatchesByDateAndCup(Time date, int cupId, int page, int limit) {
        return dao.getMatchesByDateAndCup(date, cupId, page, limit);
    }
}
