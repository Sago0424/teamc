package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    private String jdbcURL = "jdbc:mysql://localhost:3306/mydatabase";
    private String jdbcUsername = "username";
    private String jdbcPassword = "password";

    private static final String SELECT_USER_BY_ID_AND_PASSWORD = "SELECT * FROM users WHERE username = ? AND password = ?";

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            // エラーハンドリング
        } catch (ClassNotFoundException e) {
            // エラーハンドリング
        }
        return connection;
    }

    public boolean isValidUser(String username, String password) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID_AND_PASSWORD)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            // エラーハンドリング
        }
        return false;
    }
}
