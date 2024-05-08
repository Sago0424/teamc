package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;
import bean.Test;

public class SubjectDao extends Dao {

	public List<Subject> getAllSubjects() throws Exception {
	    List<Subject> subjects = new ArrayList<>();
	    Connection connection = null;
	    PreparedStatement statement = null;
	    ResultSet resultSet = null;

	    try {
	        connection = getConnection();
	        statement = connection.prepareStatement("SELECT * FROM SUBJECT WHERE deleted = false");
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
	        statement = connection.prepareStatement("SELECT * FROM SUBJECT WHERE CD = ? AND deleted = false");
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

    public List<Subject> filter(School school) throws Exception {
        List<Subject> sublist = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rSet = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement("select cd, name from subject where school_cd=? order by name");
            statement.setString(1, school.getCd());
            rSet = statement.executeQuery();

            while (rSet.next()) {
                Subject subject = new Subject();
                subject.setCd(rSet.getString("cd"));
                subject.setName(rSet.getString("name"));
                subject.setSchool(school);
                sublist.add(subject);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (rSet != null) {
                try {
                    rSet.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
        }

        return sublist;
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
            statement = connection.prepareStatement("UPDATE SUBJECT SET deleted = true WHERE CD = ?");
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
            statement.setString(3, subject.getSchool().getCd());

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
    public List<Test> getAllRounds() throws Exception {
        List<Test> rounds = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement("SELECT * FROM TEST");
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Test test = new Test();
                test.setNo(resultSet.getInt("NO"));
                rounds.add(test);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            close(resultSet, statement, connection);
        }

        return rounds;
    }

}
