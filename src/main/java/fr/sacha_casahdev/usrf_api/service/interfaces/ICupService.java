package fr.sacha_casahdev.usrf_api.service.interfaces;

import fr.sacha_casahdev.usrf_api.models.Cup;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface ICupService {
    ResponseEntity<Cup> getCupById(int id);
    ResponseEntity<List<Cup>> getCups(int page, int limit);
    ResponseEntity<List<Cup>> getCupsByScale(String scale, int page, int limit);
    ResponseEntity<Cup> addCup(Map<String, Object> cup);
    ResponseEntity<Cup> updateCup(int id, Map<String, Object> cup);
    ResponseEntity<String> deleteCup(int id);
}
