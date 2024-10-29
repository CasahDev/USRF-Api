package fr.sacha_casahdev.usrf_api.controller;

import fr.sacha_casahdev.usrf_api.models.MatchHistory;
import fr.sacha_casahdev.usrf_api.service.interfaces.IMatchHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/match/history")
public class MatchHistoryController {
    @Qualifier("matchHistoryService")
    private final IMatchHistoryService service;

    @Autowired
    public MatchHistoryController(IMatchHistoryService service) {
        this.service = service;
    }

    @GetMapping("?page={page}&size={size}")
    public ResponseEntity<List<MatchHistory>> getMatchHistories(@PathVariable int page, @PathVariable int size) {
        return service.getMatchHistories(page, size);
    }

    @GetMapping("/match/{matchId}?page={page}&size={size}")
    public ResponseEntity<List<MatchHistory>> getMatchHistoriesByMatchId(@PathVariable int matchId, @PathVariable int page, @PathVariable int size) {
        return service.getMatchHistoriesByMatchId(matchId, page, size);
    }

    @GetMapping("/event/{event}?page={page}&size={size}")
    public ResponseEntity<List<MatchHistory>> getMatchHistoriesByEvent(@PathVariable String event, @PathVariable int page, @PathVariable int size) {
        return service.getMatchHistoriesByEvent(event, page, size);
    }

    @GetMapping("/match/{matchId}/event/{event}?page={page}&size={size}")
    public ResponseEntity<List<MatchHistory>> getMatchHistoriesByMatchIdAndEvent(@PathVariable int matchId, @PathVariable String event, @PathVariable int page, @PathVariable int size) {
        return service.getMatchHistoriesByMatchIdAndEvent(matchId, event, page, size);
    }

    @GetMapping("/last/{matchId}")
    public ResponseEntity<MatchHistory> getLastMatchHistory(@PathVariable int matchId) {
        return service.getLastMatchHistory(matchId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatchHistory> getMatchHistoryById(@PathVariable int id) {
        return service.getMatchHistoryById(id);
    }

    @PostMapping("")
    public ResponseEntity<MatchHistory> createMatchHistory(MatchHistory matchHistory) {
        return service.createMatchHistory(matchHistory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MatchHistory> updateMatchHistory(@PathVariable int id, MatchHistory matchHistory) {
        return service.updateMatchHistory(id, matchHistory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMatchHistory(@PathVariable int id) {
        return service.deleteMatchHistory(id);
    }
}
