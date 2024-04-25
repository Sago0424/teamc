package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import bean.Test;

public class TestDao extends Dao {
    public boolean save(List<Test> list) {
        String insertQuery = "INSERT INTO Grades (STUDENT_NO, SUBJECT_CD, SCHOOL_CD, NO, POINT, CLASS_NUM) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = getConnection(); // DaoクラスからConnectionを取得
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            for (Test test : list) {
                preparedStatement.setInt(1, test.getStudent().getStudentNo());
                preparedStatement.setString(2, test.getSubject().getSubjectCode());
                preparedStatement.setString(3, test.getSchool().getSchool());
                preparedStatement.setInt(4, test.getNo());
                preparedStatement.setInt(5, test.getPoint());
                preparedStatement.setString(6, test.getClassNum());
                preparedStatement.addBatch();
                preparedStatement.clearParameters(); // パラメータをクリア
            }

            int[] result = preparedStatement.executeBatch();
            return result.length == list.size();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}
		return false;
    }
}
