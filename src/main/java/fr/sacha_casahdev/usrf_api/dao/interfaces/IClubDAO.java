package fr.sacha_casahdev.usrf_api.dao.interfaces;

import fr.sacha_casahdev.usrf_api.models.Club;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface IClubDAO {
    /// Get all clubs
    ///
    /// @param page  The page number
    /// @param limit The number of clubs per page
    /// @return A list of clubs with a status code (200, 500)
    ResponseEntity<List<Club>> getClubs(int page, int limit);

    /// Get a club by its id
    /// @param id The id of the club
    /// @return The club with a status code (200, 404, 500)
    ResponseEntity<Club> getClubById(int id);

    /// Get a club by its name
    /// @param name The name of the club
    /// @return The club with a status code (200, 404, 500)
    ResponseEntity<List<Club>> getClubByName(String name);

    /// Update a club
    /// @param id The id of the club
    /// @param club The new club
    /// @return A status code (200, 404, 500)
    ResponseEntity<String> updateClub(int id, Map<String, Object> club);

    /// Delete a club
    /// @param id The id of the club
    /// @return A status code (200, 404, 500)
    ResponseEntity<String> deleteClub(int id);

    /// Create a club
    /// @param club The new club
    /// @return The new club with a status code (201, 500)
    ResponseEntity<Club> createClub(Map<String, Object> club);
}
