package fr.sacha_casahdev.usrf_api.service.implementation;

import fr.sacha_casahdev.usrf_api.dao.interfaces.ICanPlayDAO;
import fr.sacha_casahdev.usrf_api.models.CanPlay;
import fr.sacha_casahdev.usrf_api.service.interfaces.ICanPlayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class CanPlayService implements ICanPlayService {
    @Qualifier("canPlayDAO")
    private final ICanPlayDAO dao;

    @Autowired
    public CanPlayService(ICanPlayDAO dao) {
        this.dao = dao;
    }

    @Override
    public ResponseEntity<CanPlay> getCanPlayById(int id) {
        return dao.getCanPlayById(id);
    }

    @Override
    public ResponseEntity<List<CanPlay>> getCanPlays(int page, int limit) {
        return dao.getCanPlays(page, limit);
    }

    @Override
    public ResponseEntity<CanPlay> addCanPlay(int player_id, String position) {
        return dao.addCanPlay(player_id, position);
    }

    @Override
    public ResponseEntity<CanPlay> updateCanPlay(int id, String position) {
        return dao.updateCanPlay(id, position);
    }

    @Override
    public ResponseEntity<String> deleteCanPlay(int id) {
        return dao.deleteCanPlay(id);
    }

    @Override
    public ResponseEntity<List<CanPlay>> getCanPlaysByPlayer(int player_id) {
        return dao.getCanPlaysByPlayer(player_id);
    }

    @Override
    public ResponseEntity<List<CanPlay>> getCanPlaysByPosition(String position) {
        return dao.getCanPlaysByPosition(position);
    }
}
