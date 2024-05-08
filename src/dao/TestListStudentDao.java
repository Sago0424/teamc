package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.TestListStudent;

public class TestListStudentDao extends Dao {

    private final String baseSql = "SELECT sub.name, sub.cd, t.no, t.point " +
            "FROM test t LEFT JOIN student s ON s.no = t.student_no LEFT JOIN subject sub ON sub.cd = t.subject_cd " +
            "WHERE s.no = ? order by sub.cd asc, t.no ";

    private List<TestListStudent> postFilter(ResultSet rSet) throws SQLException {
        List<TestListStudent> list = new ArrayList<>();
        while (rSet.next()) {
            TestListStudent testStu = new TestListStudent();
            testStu.setName(rSet.getString("name"));
            testStu.setCd(rSet.getString("cd"));
            testStu.setNo(rSet.getInt("no"));
            testStu.setPoint(rSet.getInt("point"));
            list.add(testStu);
        }
        return list;
    }

    public List<TestListStudent> filter(Student student) throws Exception {
        List<TestListStudent> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rSet = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(baseSql);
            statement.setString(1, student.getNo());
            rSet = statement.executeQuery();
            list = postFilter(rSet);
        } catch (SQLException e) {
            throw e;
        } finally {
            closeResources(connection, statement, rSet);
        }
        return list;
    }

    private void closeResources(Connection connection, PreparedStatement statement, ResultSet rSet) {
        if (rSet != null) {
            try {
                rSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}