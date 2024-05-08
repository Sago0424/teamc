package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;
import bean.TestListSubject;

public class TestListSubjectDao extends Dao {

    private String baseSql = "SELECT s.ent_year, s.class_num, s.no AS stu_no, s.name AS stu_name, " +
            "MAX(CASE WHEN t.no = 1 THEN t.point ELSE NULL END) AS point_1, " +
            "MAX(CASE WHEN t.no = 2 THEN t.point ELSE NULL END) AS point_2 " +
            "FROM `student` s " +
            "LEFT JOIN `test` t ON s.no = t.student_no " +
            "LEFT JOIN `subject` sub ON sub.cd = t.subject_cd " +
            "WHERE s.ent_year = ? AND s.class_num = ? AND sub.name = ? GROUP BY s.ent_year, s.class_num, s.no, s.name ORDER BY s.no ASC";

    private List<TestListSubject> postFilter(ResultSet rSet) throws SQLException {
        List<TestListSubject> list = new ArrayList<>();
        while (rSet.next()) {
            TestListSubject testSub = new TestListSubject();
            testSub.setEntYear(rSet.getInt("ent_year"));
            testSub.setNo(rSet.getString("no"));
            testSub.setName(rSet.getString("name"));
            testSub.setClassNum(rSet.getString("class_num"));
            testSub.putPoint(1, rSet.getInt("point_1"));
            testSub.putPoint(2, rSet.getInt("point_2"));
            list.add(testSub);
        }
        return list;
    }

    public List<TestListSubject> filter(int entYear, String classNum, Subject subject, School school) throws Exception {
        List<TestListSubject> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rSet = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(baseSql);
            statement.setInt(1, entYear);
            statement.setString(2, classNum);
            statement.setString(3, subject.getName());
            rSet = statement.executeQuery();
            list = postFilter(rSet);
        } finally {
            if (rSet != null) {
                rSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return list;
    }
}