package fr.sacha_casahdev.usrf_api.service.implementation;

import fr.sacha_casahdev.usrf_api.dao.interfaces.ITeamDAO;
import fr.sacha_casahdev.usrf_api.models.Team;
import fr.sacha_casahdev.usrf_api.service.interfaces.ITeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component("TeamService")
public class TeamService implements ITeamService {
    @Qualifier("TeamDAO")
    private ITeamDAO dao;

    @Autowired
    public TeamService(ITeamDAO dao) {
        this.dao = dao;
    }

    @Override
    public ResponseEntity<Team> getTeamById(int id) {
        return dao.getTeamById(id);
    }

    @Override
    public ResponseEntity<List<Team>> getTeams(int page, int limit) {
        return dao.getTeams(page, limit);
    }

    @Override
    public ResponseEntity<Team> addTeam(Map<String, Object> team) {
        return dao.addTeam(team);
    }

    @Override
    public ResponseEntity<Team> updateTeam(Map<String, Object> team) {
        return dao.updateTeam(team);
    }

    @Override
    public ResponseEntity<String> deleteTeam(int id) {
        return dao.deleteTeam(id);
    }
}
