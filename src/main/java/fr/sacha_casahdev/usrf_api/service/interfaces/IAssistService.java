package fr.sacha_casahdev.usrf_api.service.interfaces;

import fr.sacha_casahdev.usrf_api.models.Assist;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IAssistService {
    ResponseEntity<Assist> addAssist(Assist assist);
    ResponseEntity<String> removeAssists(int assist_id);
    ResponseEntity<List<Assist>> getAssistsByUserAndGame(int idUser, int idGame, int page, int limit);
    ResponseEntity<List<Assist>> getAssistsByUser(int idUser, int page, int limit);
    ResponseEntity<List<Assist>> getAssistsByGame(int idGame, int page, int limit);
    ResponseEntity<List<Assist>> getAssists(int page, int limit);
    ResponseEntity<String> updateAssists(int idAssist, Assist assist);
    ResponseEntity<String> deleteAssists(int idAssist);
    ResponseEntity<String> removeLastAssist(int game_id);
    ResponseEntity<Assist> getAssistById(int assistId);
}
