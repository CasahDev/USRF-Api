package fr.sacha_casahdev.usrf_api.controller;

import fr.sacha_casahdev.usrf_api.models.Cup;
import fr.sacha_casahdev.usrf_api.service.interfaces.ICupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/cups")
public class CupController {
    @Qualifier("cupService")
    private final ICupService service;

    @Autowired
    public CupController(ICupService service) {
        this.service = service;
    }

    @GetMapping("?page={page}&size={size}")
    public ResponseEntity<List<Cup>> getCups(@PathVariable int page, @PathVariable int size) {
        return service.getCups(page, size);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cup> getCupById(@PathVariable int id) {
        return service.getCupById(id);
    }

    @GetMapping("/scale/{scale}?page={page}&size={size}")
    public ResponseEntity<List<Cup>> getCupsByScale(@PathVariable String scale, @PathVariable int page, @PathVariable int size) {
        return service.getCupsByScale(scale, page, size);
    }

    @PostMapping("")
    public ResponseEntity<Cup> addCup(Cup cup) {
        return service.addCup(cup);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cup> updateCup(@PathVariable int id, Cup cup) {
        return service.updateCup(id, cup);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCup(@PathVariable int id) {
        return service.deleteCup(id);
    }
}
