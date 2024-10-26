package fr.sacha_casahdev.usrf_api.dao.interfaces;

import fr.sacha_casahdev.usrf_api.models.Club;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface IClubDAO {
    ResponseEntity<List<Club>> getClubs(int page, int limit);
    ResponseEntity<Club> getClubById(int id);
    ResponseEntity<List<Club>> getClubByName(String name);
    ResponseEntity<String> updateClub(int id, Map<String, Object> club);
    ResponseEntity<String> deleteClub(int id);
    ResponseEntity<Club> createClub(Map<String, Object> club);
}
