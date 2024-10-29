package fr.sacha_casahdev.usrf_api.controller;

import fr.sacha_casahdev.usrf_api.models.CanPlay;
import fr.sacha_casahdev.usrf_api.service.interfaces.ICanPlayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/canplay")
public class CanPlayController {
    @Qualifier("canPlayService")
    private final ICanPlayService service;

    @Autowired
    public CanPlayController(ICanPlayService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CanPlay> getCanPlayById(@PathVariable Integer id) {
        return service.getCanPlayById(id);
    }

    @GetMapping("?page={page}&size={size}")
    public ResponseEntity<List<CanPlay>> getCanPlays(@PathVariable Integer page, @PathVariable Integer size) {
        return service.getCanPlays(page, size);
    }

    @GetMapping("/player/{player_id}")
    public ResponseEntity<List<CanPlay>> getCanPlaysByPlayer(@PathVariable Integer player_id) {
        return service.getCanPlaysByPlayer(player_id);
    }

    @GetMapping("/position/{position}")
    public ResponseEntity<List<CanPlay>> getCanPlaysByPosition(@PathVariable String position) {
        return service.getCanPlaysByPosition(position);
    }

    @PostMapping("")
    public ResponseEntity<CanPlay> addCanPlay(int player_id, String position) {
        return service.addCanPlay(player_id, position);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CanPlay> updateCanPlay(@PathVariable Integer id, String position) {
        return service.updateCanPlay(id, position);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCanPlay(@PathVariable Integer id) {
        return service.deleteCanPlay(id);
    }
}
