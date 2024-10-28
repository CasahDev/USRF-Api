package fr.sacha_casahdev.usrf_api.service.interfaces;

import fr.sacha_casahdev.usrf_api.models.Assist;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface IAssistService {
    ResponseEntity<Assist> addAssists(Map<String, Object> assist);
    ResponseEntity<String> removeAssists(int assist_id);
    ResponseEntity<List<Assist>> getAssistsByUserAndGame(int idUser, int idGame);
    ResponseEntity<List<Assist>> getAssistsByUser(int idUser, int page, int limit);
    ResponseEntity<List<Assist>> getAssistsByGame(int idGame, int page, int limit);
    ResponseEntity<List<Assist>> getAssists(int page, int limit);
    ResponseEntity<String> updateAssists(int idAssist, Map<String, Object> assist);
    ResponseEntity<String> deleteAssists(int idAssist);
    ResponseEntity<String> removeLastAssist(int game_id);
}
