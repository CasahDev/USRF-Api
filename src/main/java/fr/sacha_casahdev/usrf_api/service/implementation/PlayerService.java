package fr.sacha_casahdev.usrf_api.service.implementation;

import fr.sacha_casahdev.usrf_api.dao.interfaces.IPlayerDAO;
import fr.sacha_casahdev.usrf_api.models.Player;
import fr.sacha_casahdev.usrf_api.service.interfaces.IPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component("PlayerService")
public class PlayerService implements IPlayerService {
    @Qualifier("PlayerDAO")
    private IPlayerDAO dao;

    @Autowired
    public PlayerService(IPlayerDAO dao) {
        this.dao = dao;
    }


    @Override
    public ResponseEntity<List<Player>> getPlayers(int page, int limit) {
        return dao.getPlayers(page, limit);
    }

    @Override
    public ResponseEntity<Player> getPlayerById(int id) {
        return dao.getPlayerById(id);
    }

    @Override
    public ResponseEntity<Player> createPlayer(Map<String, Object> player) {
        return dao.createPlayer(player);
    }

    @Override
    public ResponseEntity<Player> updatePlayer(int id, Map<String, Object> player) {
        return dao.updatePlayer(id, player);
    }

    @Override
    public ResponseEntity<Player> deletePlayer(int id) {
        return dao.deletePlayer(id);
    }
}
