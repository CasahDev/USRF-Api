package fr.sacha_casahdev.usrf_api.service.interfaces;

import fr.sacha_casahdev.usrf_api.models.History;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IHistoryService {

    ResponseEntity<History> createHistory(History history);

    ResponseEntity<History> getHistory(int id);

    ResponseEntity<String> deleteHistory(int id);

    ResponseEntity<History> updateHistory(int id, History history);

    ResponseEntity<List<History>> getHistories(int page, int limit);
}
