package fr.sacha_casahdev.usrf_api.dao.interfaces;

import fr.sacha_casahdev.usrf_api.models.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface IUserDAO {
    /// Get a user by its id
    /// @param id The id of the user
    /// @return The user with a status code (200, 404, 500)
    ResponseEntity<User> getUserById(int id);

    /// Get a user by its email
    /// @param email The email of the user
    /// @return The user with a status code (200, 404, 500)
    ResponseEntity<User> getUserByEmail(String email);

    /// Create a user
    /// @param user The new user
    /// @return The new user with a status code (201, 500)
    ResponseEntity<User> createUser(Map<String, Object> user);

    /// Update a user
    /// @param id The id of the user
    /// @param user The new user
    /// @return A status code (200, 404, 500)
    ResponseEntity<User> updateUser(int id, Map<String, Object> user);

    /// Delete a user
    /// @param id The id of the user
    /// @return A status code (200, 404, 500)
    ResponseEntity<String> deleteUser(int id);

    /// Login a user
    /// @param user The user to login
    /// @return The user with a status code (200, 404, 500)
    ResponseEntity<User> login(Map<String, Object> user);

    /// Get all users
    /// @param page  The page number
    /// @param limit The number of users per page
    /// @return A list of users with a status code (200, 500)
    ResponseEntity<List<User>> getUsers(int page, int limit);
}
