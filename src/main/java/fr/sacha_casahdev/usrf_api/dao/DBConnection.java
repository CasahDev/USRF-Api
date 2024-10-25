package fr.sacha_casahdev.usrf_api.dao;

import org.springframework.beans.factory.annotation.Value;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    @Value("${DATABASE_URL}")
    public static String url;

    @Value("${DATABASE_USERNAME}")
    public static String username;

    @Value("${DATABASE_PASSWORD}")
    public static String password;

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
