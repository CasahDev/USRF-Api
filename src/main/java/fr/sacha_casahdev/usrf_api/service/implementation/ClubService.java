package fr.sacha_casahdev.usrf_api.service.implementation;

import fr.sacha_casahdev.usrf_api.dao.implementation.ClubDAO;
import fr.sacha_casahdev.usrf_api.dao.interfaces.IClubDAO;
import fr.sacha_casahdev.usrf_api.models.Club;
import fr.sacha_casahdev.usrf_api.service.interfaces.IClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component("clubService")
public class ClubService implements IClubService {
    @Qualifier("clubDAO")
    private final IClubDAO dao;

    @Autowired
    public ClubService(IClubDAO dao) {
        this.dao = dao;
    }

    @Override
    public ResponseEntity<List<Club>> getClubs(int page, int limit) {
        return dao.getClubs(page, limit);
    }

    @Override
    public ResponseEntity<Club> getClubById(int id) {
        return dao.getClubById(id);
    }

    @Override
    public ResponseEntity<List<Club>> getClubByName(String name) {
        return dao.getClubByName(name);
    }

    @Override
    public ResponseEntity<String> updateClub(int id, Map<String, Object> club) {
        return dao.updateClub(id, club);
    }

    @Override
    public ResponseEntity<String> deleteClub(int id) {
        return dao.deleteClub(id);
    }

    @Override
    public ResponseEntity<Club> createClub(Map<String, Object> club) {
        return dao.createClub(club);
    }
}
