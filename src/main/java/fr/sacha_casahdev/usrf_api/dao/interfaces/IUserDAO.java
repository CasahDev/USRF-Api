package fr.sacha_casahdev.usrf_api.dao.interfaces;

import fr.sacha_casahdev.usrf_api.models.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface IUserDAO {
    ResponseEntity<User> getUserById(int id);
    ResponseEntity<User> getUserByEmail(String email);
    ResponseEntity<User> createUser(Map<String, Object> user);
    ResponseEntity<User> updateUser(int id, Map<String, Object> user);
    ResponseEntity<String> deleteUser(int id);
    ResponseEntity<User> login(Map<String, Object> user);
    ResponseEntity<List<User>> getUsers(int page, int limit);
}
