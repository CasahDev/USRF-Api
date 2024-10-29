package fr.sacha_casahdev.usrf_api.service.implementation;

import fr.sacha_casahdev.usrf_api.dao.interfaces.IAssistDAO;
import fr.sacha_casahdev.usrf_api.models.Assist;
import fr.sacha_casahdev.usrf_api.service.interfaces.IAssistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("assistService")
public class AssistService implements IAssistService {
    @Qualifier("assistDAO")
    private final IAssistDAO dao;

    @Autowired
    public AssistService(IAssistDAO dao) {
        this.dao = dao;
    }

    @Override
    public ResponseEntity<Assist> addAssist(Assist assist) {
        return dao.addAssists(assist);
    }

    @Override
    public ResponseEntity<String> removeAssists(int assist_id) {
        return dao.removeAssists(assist_id);
    }

    @Override
    public ResponseEntity<List<Assist>> getAssistsByUserAndGame(int idUser, int idGame, int page, int limit) {
        return dao.getAssistsByUserAndGame(idUser, idGame, page, limit);
    }

    @Override
    public ResponseEntity<List<Assist>> getAssistsByUser(int idUser, int page, int limit) {
        return dao.getAssistsByUser(idUser, page, limit);
    }

    @Override
    public ResponseEntity<List<Assist>> getAssistsByGame(int idGame, int page, int limit) {
        return dao.getAssistsByGame(idGame, page, limit);
    }

    @Override
    public ResponseEntity<List<Assist>> getAssists(int page, int limit) {
        return dao.getAssists(page, limit);
    }

    @Override
    public ResponseEntity<String> updateAssists(int idAssist, Assist assist) {
        return dao.updateAssists(idAssist, assist);
    }

    @Override
    public ResponseEntity<String> deleteAssists(int idAssist) {
        return dao.deleteAssists(idAssist);
    }

    @Override
    public ResponseEntity<String> removeLastAssist(int game_id) {
        return dao.removeLastAssist(game_id);
    }

    @Override
    public ResponseEntity<Assist> getAssistById(int assistId) {
        return dao.getAssistById(assistId);
    }
}
