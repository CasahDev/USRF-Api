package fr.sacha_casahdev.usrf_api.controller;

import fr.sacha_casahdev.usrf_api.models.Assist;
import fr.sacha_casahdev.usrf_api.service.interfaces.IAssistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assist")
public class AssistController {
    @Qualifier("assistService")
    private final IAssistService service;

    @Autowired
    public AssistController(IAssistService service) {
        this.service = service;
    }

    @GetMapping(value = "/{assistId}")
    public ResponseEntity<Assist> getAssistById(@PathVariable Integer assistId) {
        return service.getAssistById(assistId);
    }

    @GetMapping(value = "/{userId}?page={page}&size={size}")
    public ResponseEntity<List<Assist>> getAssistByUserId(@PathVariable Integer userId, @PathVariable Integer page, @PathVariable Integer size) {
        return service.getAssistsByUser(userId, page, size);
    }

    @GetMapping(value = "/{gameId}?page={page}&size={size}")
    public ResponseEntity<List<Assist>> getAssistByGameId(@PathVariable Integer gameId, @PathVariable Integer page, @PathVariable Integer size) {
        return service.getAssistsByGame(gameId, page, size);
    }

    @GetMapping(value = "/{userId}/{gameId}?page={page}&size={size}")
    public ResponseEntity<List<Assist>> getAssistByUserIdAndGameId(@PathVariable Integer userId, @PathVariable Integer gameId, @PathVariable Integer page, @PathVariable Integer size) {
        return service.getAssistsByUserAndGame(userId, gameId, page, size);
    }

    @GetMapping(value = "?page={page}&size={size}")
    public ResponseEntity<List<Assist>> getAssists(@PathVariable Integer page, @PathVariable Integer size) {
        return service.getAssists(page, size);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Assist> addAssist(Assist assist) {
        return service.addAssist(assist);
    }

    @PutMapping(value = "/{assistId}")
    public ResponseEntity<String> updateAssist(@PathVariable Integer assistId, @RequestBody Assist assist) {
        return service.updateAssists(assistId, assist);
    }

    @DeleteMapping(value = "/{assistId}")
    public ResponseEntity<String> deleteAssist(@PathVariable Integer assistId) {
        return service.deleteAssists(assistId);
    }

    @DeleteMapping(value = "/{gameId}/last")
    public ResponseEntity<String> removeLastAssist(@PathVariable Integer gameId) {
        return service.removeLastAssist(gameId);
    }

    @DeleteMapping(value = "/{assistId}")
    public ResponseEntity<String> removeAssist(@PathVariable Integer assistId) {
        return service.removeAssists(assistId);
    }
}
