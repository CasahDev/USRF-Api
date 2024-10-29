package fr.sacha_casahdev.usrf_api.controller;

import fr.sacha_casahdev.usrf_api.models.Match;
import fr.sacha_casahdev.usrf_api.service.interfaces.IMatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.List;

@RestController("/match")
public class MatchController {
    @Qualifier("matchService")
    private final IMatchService service;

    @Autowired
    public MatchController(IMatchService service) {
        this.service = service;
    }

    @GetMapping("{id}")
    public ResponseEntity<Match> getMatchById(@PathVariable int id) {
        return service.getMatchById(id);
    }

    @GetMapping("?page={page}&size={size}")
    public ResponseEntity<List<Match>> getMatches(@PathVariable int page, @PathVariable int size) {
        return service.getMatches(page, size);
    }

    @GetMapping("team/{teamId}?page={page}&size={size}")
    public ResponseEntity<List<Match>> getMatchesByTeam(@PathVariable int teamId, @PathVariable int page, @PathVariable int size) {
        return service.getMatchesByTeam(teamId, page, size);
    }

    @GetMapping("cup?page={page}&size={size}")
    public ResponseEntity<List<Match>> getMatchesCup(@PathVariable int page, @PathVariable int size) {
        return service.getMatchesCup(page, size);
    }

    @GetMapping("cup/{cupId}?page={page}&size={size}")
    public ResponseEntity<List<Match>> getMatchesByCup(@PathVariable int cupId, @PathVariable int page, @PathVariable int size) {
        return service.getMatchesByCup(cupId, page, size);
    }

    @GetMapping("team/{teamId}/cup/{cupId}?page={page}&size={size}")
    public ResponseEntity<List<Match>> getMatchesByTeamAndCup(@PathVariable int teamId, @PathVariable int cupId, @PathVariable int page, @PathVariable int size) {
        return service.getMatchesByTeamAndCup(teamId, cupId, page, size);
    }

    @GetMapping("date/{date}?page={page}&size={size}")
    public ResponseEntity<List<Match>> getMatchesByDate(@PathVariable Time date, @PathVariable int page, @PathVariable int size) {
        return service.getMatchesByDate(date, page, size);
    }

    @GetMapping("date/{date}/team/{teamId}?page={page}&size={size}")
    public ResponseEntity<List<Match>> getMatchesByDateAndTeam(@PathVariable Time date, @PathVariable int teamId, @PathVariable int page, @PathVariable int size) {
        return service.getMatchesByDateAndTeam(date, teamId, page, size);
    }

    @GetMapping("date/{date}/cup/{cupId}?page={page}&size={size}")
    public ResponseEntity<List<Match>> getMatchesByDateAndCup(@PathVariable Time date, @PathVariable int cupId, @PathVariable int page, @PathVariable int size) {
        return service.getMatchesByDateAndCup(date, cupId, page, size);
    }

    @PostMapping("")
    public ResponseEntity<Match> addMatch(Match match) {
        return service.addMatch(match);
    }

    @PutMapping("{id}")
    public ResponseEntity<Match> updateMatch(@PathVariable int id, @RequestBody Match match) {
        return service.updateMatch(id, match);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteMatch(@PathVariable int id) {
        return service.deleteMatch(id);
    }
}
