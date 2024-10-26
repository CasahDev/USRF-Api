package fr.sacha_casahdev.usrf_api.dao.interfaces;

import fr.sacha_casahdev.usrf_api.models.CanPlay;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ICanPlayDAO {
    ResponseEntity<CanPlay> getCanPlayById(int id);
    ResponseEntity<List<CanPlay>> getCanPlays(int page, int limit);
    ResponseEntity<CanPlay> addCanPlay(int player_id, String position);
    ResponseEntity<CanPlay> updateCanPlay(int id, String position);
    ResponseEntity<String> deleteCanPlay(int id);
    ResponseEntity<List<CanPlay>> getCanPlaysByPlayer(int player_id);
    ResponseEntity<List<CanPlay>> getCanPlaysByPosition(String position);
}
