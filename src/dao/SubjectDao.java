package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Subject;

public class SubjectDao extends Dao {

    public List<Subject> getAllSubjects() throws Exception {
        List<Subject> subjects = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement("SELECT * FROM SUBJECT");
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Subject subject = new Subject();
                subject.setCd(resultSet.getString("CD"));
                subject.setName(resultSet.getString("NAME"));
                subjects.add(subject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            close(resultSet, statement, connection);
        }

        return subjects;
    }

    public Subject get(String subjectCd) throws Exception {
        Subject subject = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement("SELECT * FROM SUBJECT WHERE CD = ?");
            statement.setString(1, subjectCd);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                subject = new Subject();
                subject.setCd(resultSet.getString("CD"));
                subject.setName(resultSet.getString("NAME"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            close(resultSet, statement, connection);
        }

        return subject;
    }

    public boolean update(Subject subject) throws Exception {
        boolean success = false;
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement("UPDATE SUBJECT SET NAME = ? WHERE CD = ?");
            statement.setString(1, subject.getName());
            statement.setString(2, subject.getCd());

            int rowsAffected = statement.executeUpdate();
            success = (rowsAffected > 0);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            close(null, statement, connection);
        }

        return success;
    }

    public boolean delete(String cd) throws Exception {
        boolean success = false;
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement("DELETE FROM SUBJECT WHERE CD = ?");
            statement.setString(1, cd);

            int rowsAffected = statement.executeUpdate();
            success = (rowsAffected > 0);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            close(null, statement, connection);
        }

        return success;
    }

    public boolean save(Subject subject) throws Exception {
        boolean success = false;
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement("INSERT INTO SUBJECT (CD, NAME, SCHOOL_CD) VALUES (?, ?, ?)");
            statement.setString(1, subject.getCd());
            statement.setString(2, subject.getName());
            statement.setString(3, subject.getSchool_Cd());

            int rowsAffected = statement.executeUpdate();
            success = (rowsAffected > 0);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            close(null, statement, connection);
        }

        return success;
    }

    private void close(ResultSet resultSet, PreparedStatement statement, Connection connection) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
