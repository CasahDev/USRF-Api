package fr.sacha_casahdev.usrf_api.service.implementation;

import fr.sacha_casahdev.usrf_api.dao.interfaces.IHistoryDAO;
import fr.sacha_casahdev.usrf_api.models.History;
import fr.sacha_casahdev.usrf_api.service.interfaces.IHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("historyService")
public class HistoryService implements IHistoryService {
    @Qualifier("historyDAO")
    private IHistoryDAO dao;

    @Autowired
    public HistoryService(IHistoryDAO dao) {
        this.dao = dao;
    }

    @Override
    public ResponseEntity<History> createHistory(History history) {
        return dao.createHistory(history);
    }

    @Override
    public ResponseEntity<History> getHistory(int id) {
        return dao.getHistory(id);
    }

    @Override
    public ResponseEntity<String> deleteHistory(int id) {
        return dao.deleteHistory(id);
    }

    @Override
    public ResponseEntity<History> updateHistory(int id, History history) {
        return dao.updateHistory(id, history);
    }

    @Override
    public ResponseEntity<List<History>> getHistories(int page, int limit) {
        return dao.getHistories(page, limit);
    }
}
