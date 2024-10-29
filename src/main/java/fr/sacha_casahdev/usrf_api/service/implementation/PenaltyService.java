package fr.sacha_casahdev.usrf_api.service.implementation;

import fr.sacha_casahdev.usrf_api.dao.interfaces.IPenaltyDAO;
import fr.sacha_casahdev.usrf_api.models.Penalty;
import fr.sacha_casahdev.usrf_api.service.interfaces.IPenaltyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component("PenaltyService")
public class PenaltyService implements IPenaltyService {
    @Qualifier("PenaltyDAO")
    private IPenaltyDAO dao;

    @Autowired
    public PenaltyService(IPenaltyDAO dao) {
        this.dao = dao;
    }

    @Override
    public ResponseEntity<Penalty> addPenalty(Map<String, Object> penalty) {
        return dao.addPenalty(penalty);
    }

    @Override
    public ResponseEntity<String> removePenalty(int penalty_id) {
        return dao.removePenalty(penalty_id);
    }

    @Override
    public ResponseEntity<List<Penalty>> getPenaltiesByUserAndGame(int idUser, int idGame) {
        return dao.getPenaltiesByUserAndGame(idUser, idGame);
    }

    @Override
    public ResponseEntity<List<Penalty>> getPenaltiesByUser(int idUser, int page, int limit) {
        return dao.getPenaltiesByUser(idUser, page, limit);
    }

    @Override
    public ResponseEntity<List<Penalty>> getPenaltiesByGame(int idGame, int page, int limit) {
        return dao.getPenaltiesByGame(idGame, page, limit);
    }

    @Override
    public ResponseEntity<List<Penalty>> getPenalties(int page, int limit) {
        return dao.getPenalties(page, limit);
    }

    @Override
    public ResponseEntity<String> updatePenalty(int idPenalty, Map<String, Object> penalty) {
        return dao.updatePenalty(idPenalty, penalty);
    }

    @Override
    public ResponseEntity<String> deletePenalty(int idPenalty) {
        return dao.deletePenalty(idPenalty);
    }

    @Override
    public ResponseEntity<String> removeLastPenalty(int game_id) {
        return dao.removeLastPenalty(game_id);
    }
}
