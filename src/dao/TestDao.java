package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Test;

public class TestDao extends Dao {

    // テーブル名やカラム名は適切に変更してください
    private static final String SELECT_ALL_TESTS = "SELECT * FROM TEST";
    private static final String INSERT_TEST = "INSERT INTO TEST (STUDENT_NO, SUBJECT_CD, SCHOOL_CD, NO, POINT, CLASS_NUM) VALUES (?, ?, ?, ?, ?, ?)";

    // 全てのテスト情報を取得するメソッド
    public List<Test> getAllTests() throws Exception {
        List<Test> tests = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(SELECT_ALL_TESTS);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Test test = new Test();
                test.setStudentNo(resultSet.getInt("STUDENT_NO"));
                test.setSubjectCd(resultSet.getString("SUBJECT_CD"));
                test.setSchoolCd(resultSet.getString("SCHOOL_CD"));
                test.setNo(resultSet.getInt("NO"));
                test.setPoint(resultSet.getInt("POINT"));
                test.setClassNum(resultSet.getString("CLASS_NUM"));
                tests.add(test);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            close(resultSet, statement, connection);
        }

        return tests;
    }

    private void close(ResultSet resultSet, PreparedStatement statement, Connection connection) {
		// TODO 自動生成されたメソッド・スタブ

	}

	// テスト情報を保存するメソッド
    public boolean save(Test test) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(INSERT_TEST);
            statement.setInt(1, test.getStudentNo());
            statement.setString(2, test.getSubjectCd());
            statement.setString(3, test.getSchoolCd());
            statement.setInt(4, test.getNo());
            statement.setInt(5, test.getPoint());
            statement.setString(6, test.getClassNum());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            close(null, statement, connection);
        }
    }
    public List<Test> filterTests(int studentNo) throws Exception {
        List<Test> filteredTests = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection(); // Implement your getConnection() method
            // Assuming you have a table named "TEST" with columns "STUDENT_NO", "SUBJECT_CD", etc.
            String filterQuery = "SELECT * FROM TEST WHERE STUDENT_NO = ?";
            statement = connection.prepareStatement(filterQuery);
            statement.setInt(1, studentNo);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Test test = new Test();
                test.setStudentNo(resultSet.getInt("STUDENT_NO"));
                test.setSubjectCd(resultSet.getString("SUBJECT_CD"));
                test.setSchoolCd(resultSet.getString("SCHOOL_CD"));
                test.setNo(resultSet.getInt("NO"));
                test.setPoint(resultSet.getInt("POINT"));
                test.setClassNum(resultSet.getString("CLASS_NUM"));
                filteredTests.add(test);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            close(resultSet, statement, connection); // Implement your close() method
        }

        return filteredTests;
    }
}
