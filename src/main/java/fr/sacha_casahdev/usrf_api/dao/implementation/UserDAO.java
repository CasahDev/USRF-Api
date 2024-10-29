package fr.sacha_casahdev.usrf_api.dao.implementation;

import fr.sacha_casahdev.usrf_api.dao.DBConnection;
import fr.sacha_casahdev.usrf_api.dao.interfaces.IUserDAO;
import fr.sacha_casahdev.usrf_api.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component("userDAO")
public class UserDAO implements IUserDAO {
    @Override
    public ResponseEntity<User> getUserById(int id) {
        ResponseEntity<User> response;
        try {
            Connection connection = DBConnection.getConnection();

            if (connection == null) {
                throw new Exception("Connection to the database failed.");
            }

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            User user = new User();
            user.setId(id);
            user.setEmail(rs.getString("email"));
            user.setFirst_name(rs.getString("first_name"));
            user.setLast_name(rs.getString("last_name"));
            user.set_admin(rs.getBoolean("is_admin"));
            user.setCreated_at(rs.getDate("created_at"));
            user.setRelated_to(new ClubDAO().getClubById(rs.getInt("related_to")).getBody());

            response = ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(500).header("error", e.getMessage()).body(null);
        }

        return response;
    }

        @Override
    public ResponseEntity<User> getUserByEmail(String email) {
            ResponseEntity<User> response;
            try {
                Connection connection = DBConnection.getConnection();

                if (connection == null) {
                    throw new Exception("Connection to the database failed.");
                }

                PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE email = ?");
                statement.setString(1, email);

                ResultSet rs = statement.executeQuery();

                User user = new User();
                user.setId(rs.getInt("id"));
                user.setEmail(email);
                user.setFirst_name(rs.getString("first_name"));
                user.setLast_name(rs.getString("last_name"));
                user.set_admin(rs.getBoolean("is_admin"));
                user.setCreated_at(rs.getDate("created_at"));
                user.setRelated_to(new ClubDAO().getClubById(rs.getInt("related_to")).getBody());

                response = ResponseEntity.ok(user);
            } catch (Exception e) {
                return ResponseEntity.status(500).header("error", e.getMessage()).body(null);
            }

            return response;
    }

    @Override
    public ResponseEntity<User> createUser(Map<String, Object> user) {
        ResponseEntity<User> response;

        try {
            Connection connection = DBConnection.getConnection();

            if (connection == null) {
                throw new Exception("Connection to the database failed.");
            }

            PreparedStatement statement = connection.prepareStatement("INSERT INTO users (email, first_name, last_name, is_admin, related_to) VALUES (?, ?, ?, ?, ?)");
            statement.setString(1, (String) user.get("email"));
            statement.setString(2, (String) user.get("first_name"));
            statement.setString(3, (String) user.get("last_name"));
            statement.setBoolean(4, (Boolean) user.get("is_admin"));
            statement.setInt(5, (Integer) user.get("related_to"));

            statement.executeUpdate();

            response = ResponseEntity.ok(new User());
        } catch (Exception e) {
            return ResponseEntity.status(500).header("error", e.getMessage()).body(null);
        }

        return response;
    }

    @Override
    public ResponseEntity<User> updateUser(int id, Map<String, Object> user) {
        ResponseEntity<User> response;

        try {
            Connection connection = DBConnection.getConnection();

            if (connection == null) {
                throw new Exception("Connection to the database failed.");
            }

            PreparedStatement statement = connection.prepareStatement("UPDATE users SET email = ?, first_name = ?, last_name = ?, is_admin = ?, related_to = ? WHERE id = ?");
            statement.setString(1, (String) user.get("email"));
            statement.setString(2, (String) user.get("first_name"));
            statement.setString(3, (String) user.get("last_name"));
            statement.setBoolean(4, (Boolean) user.get("is_admin"));
            statement.setInt(5, (Integer) user.get("related_to"));
            statement.setInt(6, id);

            statement.executeUpdate();

            User u = new User();
            u.setId(id);
            u.setEmail((String) user.get("email"));
            u.setFirst_name((String) user.get("first_name"));
            u.setLast_name((String) user.get("last_name"));
            u.set_admin((Boolean) user.get("is_admin"));
            u.setRelated_to(new ClubDAO().getClubById((Integer) user.get("related_to")).getBody());


            response = ResponseEntity.ok(new User());
        } catch (Exception e) {
            return ResponseEntity.status(500).header("error", e.getMessage()).body(null);
        }

        return response;
    }

    @Override
    public ResponseEntity<String> deleteUser(int id) {
        ResponseEntity<String> response;

        try {
            Connection connection = DBConnection.getConnection();

            if (connection == null) {
                throw new Exception("Connection to the database failed.");
            }

            PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE id = ?");
            statement.setInt(1, id);

            statement.executeUpdate();

            response = ResponseEntity.ok("User deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(500).header("error", e.getMessage()).body(null);
        }

        return response;
    }

    @Override
    public ResponseEntity<User> login(Map<String, Object> user) {
        ResponseEntity<User> response;

        try {
            Connection connection = DBConnection.getConnection();

            if (connection == null) {
                throw new Exception("Connection to the database failed.");
            }

            PreparedStatement statement = connection.prepareStatement("SELECT password, salt FROM users WHERE email = ?");
            statement.setString(1, (String) user.get("email"));

            ResultSet rs = statement.executeQuery();

            if (!rs.next()) {
                return ResponseEntity.status(401).header("error", "User not found.").body(null);
            }

            String password = (String) user.get("password");
            String salt = rs.getString("salt");
            String passwordToHash = password + salt;
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++){
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            String generatedPassword = sb.toString();

            if (!generatedPassword.equals(rs.getString("password"))) {
                return ResponseEntity.status(401).header("error", "Invalid password.").body(null);
            }

            statement = connection.prepareStatement("SELECT * FROM users WHERE email = ?");
            statement.setString(1, (String) user.get("email"));

            rs = statement.executeQuery();

            User u = new User();
            u.setId(rs.getInt("id"));
            u.setEmail((String) user.get("email"));
            u.setFirst_name(rs.getString("first_name"));
            u.setLast_name(rs.getString("last_name"));
            u.set_admin(rs.getBoolean("is_admin"));
            u.setCreated_at(rs.getDate("created_at"));
            u.setRelated_to(new ClubDAO().getClubById(rs.getInt("related_to")).getBody());

            response = ResponseEntity.ok(u);
        } catch (Exception e) {
            return ResponseEntity.status(500).header("error", e.getMessage()).body(null);
        }

        return response;
    }

    @Override
    public ResponseEntity<List<User>> getUsers(int page, int limit) {
        ResponseEntity<List<User>> response;

        try {
            Connection connection = DBConnection.getConnection();

            if (connection == null) {
                throw new Exception("Connection to the database failed.");
            }

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users LIMIT ? OFFSET ?");
            statement.setInt(1, limit);
            statement.setInt(2, (page - 1) * limit);

            ResultSet rs = statement.executeQuery();

            List<User> users = new ArrayList<>();

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("email"));
                user.setFirst_name(rs.getString("first_name"));
                user.setLast_name(rs.getString("last_name"));
                user.set_admin(rs.getBoolean("is_admin"));
                user.setCreated_at(rs.getDate("created_at"));
                user.setRelated_to(new ClubDAO().getClubById(rs.getInt("related_to")).getBody());

                users.add(user);
            }

            response = ResponseEntity.ok(users);
        } catch (Exception e) {
            return ResponseEntity.status(500).header("error", e.getMessage()).body(null);
        }

        return response;
    }
}
