package fr.sacha_casahdev.usrf_api.service.implementation;

import fr.sacha_casahdev.usrf_api.dao.interfaces.ICupDAO;
import fr.sacha_casahdev.usrf_api.models.Cup;
import fr.sacha_casahdev.usrf_api.service.interfaces.ICupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CupService implements ICupService {
    @Qualifier("cupDAO")
    private final ICupDAO dao;

    @Autowired
    public CupService(ICupDAO dao) {
        this.dao = dao;
    }


    @Override
    public ResponseEntity<Cup> getCupById(int id) {
        return dao.getCupById(id);
    }

    @Override
    public ResponseEntity<List<Cup>> getCups(int page, int limit) {
        return dao.getCups(page, limit);
    }

    @Override
    public ResponseEntity<List<Cup>> getCupsByScale(String scale, int page, int limit) {
        return dao.getCupsByScale(scale, page, limit);
    }

    @Override
    public ResponseEntity<Cup> addCup(Cup cup) {
        return dao.addCup(cup);
    }

    @Override
    public ResponseEntity<Cup> updateCup(int id, Cup cup) {
        return dao.updateCup(id, cup);
    }

    @Override
    public ResponseEntity<String> deleteCup(int id) {
        return dao.deleteCup(id);
    }
}
