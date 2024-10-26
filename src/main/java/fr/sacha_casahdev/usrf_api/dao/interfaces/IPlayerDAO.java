package fr.sacha_casahdev.usrf_api.dao.interfaces;

import fr.sacha_casahdev.usrf_api.models.Player;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface IPlayerDAO {
    ResponseEntity<List<Player>> getPlayers(int page, int limit);

    ResponseEntity<Player> getPlayerById(int id);

    ResponseEntity<Player> createPlayer(Map<String, Object> player);

    ResponseEntity<Player> updatePlayer(int id, Map<String, Object> player);

    ResponseEntity<Player> deletePlayer(int id);
}
