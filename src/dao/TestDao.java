package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;

public class TestDao {

	// テーブル名やカラム名は適切に変更してください
	private static final String SELECT_ALL_TESTS = "SELECT * FROM TEST WHERE NO=?";
	private static final String INSERT_TEST = "INSERT INTO TEST (STUDENT_NO, SUBJECT_CD, SCHOOL_CD, NO, POINT, CLASS_NUM) VALUES (?, ?, ?, ?, ?, ?)";
	// 全てのテスト情報を取得するメソッド
	public List<Test> getAllTests() throws Exception {
		List<Test> list = new ArrayList<>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = getConnection();
			statement = connection.prepareStatement(SELECT_ALL_TESTS);
			resultSet = statement.executeQuery();
			School school = new School();
			Test test = new Test();
			Student student = new Student();
			Subject subject = new Subject();

			if (resultSet.next()) {
				test.setStudent(student.get(resultSet.getString("student_no")));
				test.setSubject(subject.get(resultSet.getString("subject_cd")));
				test.setSchool(school.get(resultSet.getString("school_cd")));
				test.setNo(resultSet.getInt("NO"));
				test.setPoint(resultSet.getInt("POINT"));
				test.setClassNum(resultSet.getString("CLASS_NUM"));
				list.add(test);
			}else{
				test = null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			close(resultSet, statement, connection);
		}

		return list;
	}

	// テスト情報を保存するメソッド
	public boolean save(Test test) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			connection = getConnection();
			statement = connection.prepareStatement(INSERT_TEST);
			// ここでPreparedStatementのパラメータを設定します
			// 例: statement.setInt(1, test.getStudentNo());
			// パラメータのインデックスと型は実際のデータベースのカラムに合わせてください
			int rowsAffected = statement.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			close(null, statement, connection);
		}
	}

	private String baseSql = "select * from test where school_cd=? and subject_cd=?";

	public List<Test> filter(int studentNo, String subjectCd) throws Exception {
		List<Test> tests = new ArrayList<>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		// クエリの作成
		String query = "SELECT * FROM TEST WHERE STUDENT_NO = ? AND SUBJECT_CD = ?";
		try {
			connection = getConnection();
			statement = connection.prepareStatement(query);
			// クエリのプレースホルダーに引数の値を設定
			statement.setInt(1, studentNo);
			statement.setString(2, subjectCd);

			// クエリを実行して結果セットを取得
			resultSet = statement.executeQuery();

			// 結果セットを反復してTestオブジェクトにマッピング
			while (resultSet.next()) {
				Test test = new Test();
				// Testオブジェクトの各フィールドを結果セットから設定
				// 例: test.setStudentNo(resultSet.getInt("STUDENT_NO"));
				tests.add(test);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			// リソースを閉じる
			close(resultSet, statement, connection);
		}

		// フィルタ条件に合致するテスト情報のリストを返す
		return tests;
	}


	// データベース接続を取得するメソッド
	private Connection getConnection() throws SQLException {
		// データベース接続情報を使用して接続を取得
		return DriverManager.getConnection(null);
	}

	private List<Test> postFilter(ResultSet rSet, School school, Subject subject) throws Exception {
		// リストを初期化
		List<Test> list = new ArrayList<>();
		try {
			// リザルトセットを全権走査
			while (rSet.next()) {
				// 学生インスタンスを初期化
				Test test = new Test();
				// 学生インスタンスに検索結果をセット
				test.setNo(rSet.getInt("no"));
				test.setPoint(rSet.getInt("name"));
				test.setClassNum(rSet.getString("class_num"));
				test.setSubject(subject);
				test.setSchool(school);
				// リストに追加
				list.add(test);
			}
		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();
		}

		return list;
	}
	public List<Test> filter(School school, Subject subject, String no, String name, Integer classnum) throws Exception {
		// リストを初期化
		List<Test> list = new ArrayList<>();
		// コネクションを確立
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;
		// リザルトセット
		ResultSet rSet = null;
		// SQL文の条件
		String condition = "and school_cd=? and class_num=?";
		// SQL文のソート
		String order = " order by no asc";


		try {
			// プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement(baseSql + condition + order);
			// プリペアードステートメントに学校コードをバインド
			statement.setString(1, school.getCd());
			// プリペアードステートメントに入学年度をバインド
			statement.setInt(2, classnum);
			// プリペアードステートメントにクラス番号をバインド
			statement.setString(3, subject.getCd());
			statement.setString(4, no);
			statement.setString(5, name);
			// プライベートステートメントを実行
			rSet = statement.executeQuery();
			// リストへの格納処理を実行
			list = postFilter(rSet, school, subject);
		} catch (Exception e) {
			throw e;
		} finally {
			// プリペアードステートメントを閉じる
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
			// コネクションを閉じる
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}

		return list;
	}
	// リソースを閉じるヘルパーメソッド
	private void close(ResultSet resultSet, PreparedStatement statement, Connection connection) {
		try {
			// ResultSetを閉じる
			if (resultSet != null) {
				resultSet.close();
			}

			// PreparedStatementを閉じる
			if (statement != null) {
				statement.close();
			}

			// Connectionを閉じる
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			// リソースを閉じる際のエラーをログに記録
			e.printStackTrace();
		}
	}
}