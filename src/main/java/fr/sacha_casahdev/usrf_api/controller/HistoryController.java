package fr.sacha_casahdev.usrf_api.controller;

import fr.sacha_casahdev.usrf_api.models.History;
import fr.sacha_casahdev.usrf_api.service.interfaces.IHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/history")
public class HistoryController {
    @Qualifier("historyService")
    private final IHistoryService service;

    @Autowired
    public HistoryController(IHistoryService service) {
        this.service = service;
    }

    @GetMapping("?page={page}&size={size}")
    public ResponseEntity<List<History>> getHistory(@PathVariable int page, @PathVariable int size) {
        return service.getHistories(page, size);
    }

    @GetMapping("/{id}")
    public ResponseEntity<History> getHistory(@PathVariable int id) {
        return service.getHistory(id);
    }

    @PostMapping("")
    public ResponseEntity<History> createHistory(History history) {
        return service.createHistory(history);
    }

    @PutMapping("/{id}")
    public ResponseEntity<History> updateHistory(@PathVariable int id, @RequestBody History history) {
        return service.updateHistory(id, history);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteHistory(@PathVariable int id) {
        return service.deleteHistory(id);
    }
}
