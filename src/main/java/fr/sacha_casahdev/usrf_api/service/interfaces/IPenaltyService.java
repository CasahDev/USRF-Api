package fr.sacha_casahdev.usrf_api.service.interfaces;

import fr.sacha_casahdev.usrf_api.models.Penalty;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface IPenaltyService {
    ResponseEntity<Penalty> addPenalty(Map<String, Object> penalty);
    ResponseEntity<String> removePenalty(int penalty_id);
    ResponseEntity<List<Penalty>> getPenaltiesByUserAndGame(int idUser, int idGame);
    ResponseEntity<List<Penalty>> getPenaltiesByUser(int idUser, int page, int limit);
    ResponseEntity<List<Penalty>> getPenaltiesByGame(int idGame, int page, int limit);
    ResponseEntity<List<Penalty>> getPenalties(int page, int limit);
    ResponseEntity<String> updatePenalty(int idPenalty, Map<String, Object> penalty);
    ResponseEntity<String> deletePenalty(int idPenalty);
    ResponseEntity<String> removeLastPenalty(int game_id);
}
