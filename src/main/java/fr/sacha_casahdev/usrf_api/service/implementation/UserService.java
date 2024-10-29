package fr.sacha_casahdev.usrf_api.service.implementation;

import fr.sacha_casahdev.usrf_api.dao.interfaces.IUserDAO;
import fr.sacha_casahdev.usrf_api.models.User;
import fr.sacha_casahdev.usrf_api.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component("userService")
public class UserService implements IUserService {
    @Qualifier("userDAO")
    private IUserDAO dao;

    @Autowired
    public UserService(IUserDAO dao) {
        this.dao = dao;
    }

    @Override
    public ResponseEntity<User> getUserById(int id) {
        return dao.getUserById(id);
    }

    @Override
    public ResponseEntity<User> getUserByEmail(String email) {
        return dao.getUserByEmail(email);
    }

    @Override
    public ResponseEntity<User> createUser(Map<String, Object> user) {
        return dao.createUser(user);
    }

    @Override
    public ResponseEntity<User> updateUser(int id, Map<String, Object> user) {
        return dao.updateUser(id, user);
    }

    @Override
    public ResponseEntity<String> deleteUser(int id) {
        return dao.deleteUser(id);
    }

    @Override
    public ResponseEntity<User> login(Map<String, Object> user) {
        return dao.login(user);
    }

    @Override
    public ResponseEntity<List<User>> getUsers(int page, int limit) {
        return dao.getUsers(page, limit);
    }
}
