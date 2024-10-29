package fr.sacha_casahdev.usrf_api.service.interfaces;

import fr.sacha_casahdev.usrf_api.models.Team;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface ITeamService {
    ResponseEntity<Team> getTeamById(int id);
    ResponseEntity<List<Team>> getTeams(int page, int limit);
    ResponseEntity<Team> addTeam(Map<String, Object> team);
    ResponseEntity<Team> updateTeam(Map<String, Object> team);
    ResponseEntity<String> deleteTeam(int id);
}
