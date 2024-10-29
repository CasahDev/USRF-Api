package fr.sacha_casahdev.usrf_api.service.interfaces;

import fr.sacha_casahdev.usrf_api.models.History;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface IHistoryService {

    ResponseEntity<History> createHistory(Map<String, Object> history);

    ResponseEntity<History> getHistory(int id);

    ResponseEntity<String> deleteHistory(int id);

    ResponseEntity<History> updateHistory(int id, Map<String, Object> history);

    ResponseEntity<String> getHistories(int page, int limit);
}
