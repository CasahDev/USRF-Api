package fr.sacha_casahdev.usrf_api.controller;

import fr.sacha_casahdev.usrf_api.models.Club;
import fr.sacha_casahdev.usrf_api.service.interfaces.IClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/club")
public class ClubController {
    @Qualifier("clubService")
    private final IClubService service;

    @Autowired
    public ClubController(IClubService service) {
        this.service = service;
    }

    @GetMapping("?page={page}&size={size}")
    public ResponseEntity<List<Club>> getClubs(@PathVariable Integer page, @PathVariable Integer size) {
        return service.getClubs(page, size);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Club> getClubById(@PathVariable Integer id) {
        return service.getClubById(id);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Club>> getClubByName(@PathVariable String name) {
        return service.getClubByName(name);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateClub(@PathVariable Integer id, Club club) {
        return service.updateClub(id, club);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClub(@PathVariable Integer id) {
        return service.deleteClub(id);
    }

    @PostMapping("/")
    public ResponseEntity<Club> createClub(@RequestBody Club club) {
        return service.createClub(club);
    }
}
