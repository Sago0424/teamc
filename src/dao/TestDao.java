package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;

public class TestDao extends Dao {
	private String baseSql = " select * from test where school_cd=?  ";

	// フィルター後のリストへの格納処理をするメソッド
	private List<Test> postFilter(ResultSet rSet, School school) throws Exception {
		// リストの初期化
		List<Test> list = new ArrayList<>();
		try {
			StudentDao stuDao = new StudentDao();
			SubjectDao subDao = new SubjectDao();
			SchoolDao schDao = new SchoolDao();
			// リザルトを全権走査
			while (rSet.next()) {
				// 学生インスタンスを初期化
				Test tests = new Test();
				// 学生インスタンスを初期化
				tests.setStudent(stuDao.get(rSet.getString("student_no")));
				tests.setClassNum(rSet.getString("class_num"));
				tests.setSubject(subDao.get(rSet.getString("subject_cd")));
				tests.setSchool(schDao.get(rSet.getString("school_cd")));
				tests.setNo(rSet.getInt("no"));
				tests.setPoint(rSet.getInt("point"));
				tests.getStudent().getEntYear();

				// リストに追加
				list.add(tests);
			}
		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();
		}

		// listを返す
		return list;
	}

	public List<Test> filter(School school, int entYear, String classNum, Subject subject, int num) throws Exception {
		// リストを初期化
		List<Test> list = new ArrayList<>();
		// コネクションを確率
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;
		// リザルトセット
		ResultSet rSet = null;
		// SQL文の条件
		String condition = " and class_num = ? and subject_cd = ? and no = ? ";
		// SQL文のソート
		String order = " order by student_no asc ";

		// SQL文の在学フラグ条件
		String conditionIsAttend = "";

		try {
			// プリペアードステートメントにSQｌ文をセット
			statement = connection.prepareStatement(baseSql + condition + conditionIsAttend + order);
			// プリペアードステートメントに学校コードをバインド
			statement.setString(1, school.getCd());

			// プリペアードステートメントに入学年度をバインド

			// プリペアードステートメントにクラス番号をバインド
			statement.setString(2, classNum);

			statement.setString(3, subject.getCd());

			statement.setInt(4, num);

			// プライベートステートメントを実行
			rSet = statement.executeQuery();

			// リストへの格納処理を実行
			list = postFilter(rSet, school);
		} catch (Exception e) {
			throw e;
		} finally {
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
		// とってきたデータの数分ループ

		// listを返す
		return list;

	}

	public Test get(Student student, Subject subject, School school, int no) throws Exception {

		Connection connection = getConnection();

		PreparedStatement statement = null;
		Test test = new Test();

		try {

			statement = connection.prepareStatement(
					"select * from test where  no = ? and student_no = ? and subject_cd = ? and school_cd = ? ");

			statement.setInt(1, no);
			statement.setString(2, student.getNo());
			statement.setString(3, subject.getCd());
			statement.setString(4, school.getCd());

			ResultSet rSet = statement.executeQuery();

			if (rSet.next()) {
				StudentDao stuDao = new StudentDao();
				SubjectDao subDao = new SubjectDao();
				SchoolDao schDao = new SchoolDao();
				test.setStudent(stuDao.get(rSet.getString("student_no")));
				test.setClassNum(rSet.getString("class_num"));
				test.setSubject(subDao.get(rSet.getString("subject_cd")));
				test.setSchool(schDao.get(rSet.getString("school_cd")));
				test.setNo(rSet.getInt("no"));
				test.setPoint(rSet.getInt("point"));

			} else {
				test = null;
			}
		} catch (Exception e) {
			throw e;
		} finally {
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
		return test;

	}

	public boolean save(Test test, Connection connection) throws Exception {

		PreparedStatement statement = null;

		int count = 0;
		try {

			Test old = get(test.getStudent(), test.getSubject(), test.getSchool(), test.getNo());

			if (old == null) {
				statement = connection.prepareStatement(
						"insert into test(student_no, subject_cd, school_cd, point, no,class_num) values(?, ? ,?, ? ,?,?)");

				statement.setString(1, test.getStudent().getNo());
				statement.setString(2, test.getSubject().getCd());
				statement.setString(3, test.getSchool().getCd());
				statement.setInt(4, test.getPoint());

				statement.setInt(5, test.getNo());
				statement.setString(6, test.getClassNum());
			} else {
				statement = connection.prepareStatement(
						"update test set point = ? where student_no = ? and subject_cd = ? and school_cd = ? and no = ?");

				statement.setInt(1, test.getPoint());
				statement.setString(2, test.getStudent().getNo());
				statement.setString(3, test.getSubject().getCd());
				statement.setString(4, test.getSchool().getCd());

				statement.setInt(5, test.getNo());

			}
			count = statement.executeUpdate();
		} catch (Exception e) {
			throw e;
		}

		if (count > 0) {
			return true;
		} else {
			return false;
		}

	}

public boolean save(List<Test> list) throws Exception {
	// リストを初期化
	int count = 0;
	// コネクションを確率
	Connection connection = getConnection();

	// プリペアードステートメント
	PreparedStatement statement = null;
	// リザルトセット
	ResultSet rSet = null;
	// SQL文の条件
	String condition = "and ent_year = ?  and class_num = ? and subject_cd = ? and no = ?";
	// SQL文のソート
	String order = " order by no asc ";

	// SQL文の在学フラグ条件
	String conditionIsAttend = "";
	boolean t = false;

	try {
		int size = list.size();
		for (int i = 0; i < size; i++) {
			t = save(list.get(i), connection);
			if (t = true) {
				count++;
			}
		}

		// リストへの格納処理を実行

	} catch (Exception e) {
		throw e;
	} finally {
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
	// とってきたデータの数分ループ

	// listを返す
	if (count > 0) {
		return true;
	} else {
		return false;
	}
}

public boolean delete(Student student) throws Exception {
	// コネクションを確立
	Connection connection = getConnection();
	// プリペアードステートメント
	PreparedStatement statement = null;
	// 実行件数
	int count = 0;

	try {
		// プリペアードステートメントにDELETE文をセット
		statement = connection.prepareStatement("update student set is_active = false where no = ?");
		// プリペアードステートメントに学生番号をバインド
		statement.setString(1, student.getNo());
		// プリペアードステートメントを実行
		count = statement.executeUpdate();
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

	if (count > 0) {
		// 実行件数が1件以上ある場合
		return true;
	} else {
		// 実行件数が0件の場合
		return false;
	}
}

public List<Subject> getAllSubjects() throws Exception {
	List<Subject> subjects = new ArrayList<>();
	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet resultSet = null;

	try {
		connection = getConnection();
		statement = connection.prepareStatement("SELECT * FROM subject");
		resultSet = statement.executeQuery();

		while (resultSet.next()) {
			Subject subject = new Subject();
			subject.setCd(resultSet.getString("subject_cd"));
			subject.setName(resultSet.getString("subject_name"));
			// 他の属性も必要に応じて設定

			subjects.add(subject);
		}
	} catch (SQLException e) {
		e.printStackTrace();
		throw e;
	} finally {
		if (resultSet != null) {
			resultSet.close();
		}
		if (statement != null) {
			statement.close();
		}
		if (connection != null) {
			connection.close();
		}
	}

	return subjects;
}

public List<Integer> setAllno() throws Exception {
	List<Integer> numbers = new ArrayList<>();
	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet resultSet = null;

	try {
		connection = getConnection();
		statement = connection.prepareStatement("SELECT * FROM test");
		resultSet = statement.executeQuery();

		while (resultSet.next()) {
			int number = resultSet.getInt("no");
			numbers.add(number);
		}
	} catch (SQLException e) {
		e.printStackTrace();
		throw e;
	} finally {
		if (resultSet != null) {
			resultSet.close();
		}
		if (statement != null) {
			statement.close();
		}
		if (connection != null) {
			connection.close();
		}
	}

	return numbers;
}

public boolean save(Test test) throws Exception {
	Connection connection = null;
	PreparedStatement statement = null;
	boolean success = false;

	try {
		connection = getConnection();
		String query = "INSERT INTO test (student_no, subject_cd, school_cd, point, no, class_num) VALUES (?, ?, ?, ?, ?, ?)";
		statement = connection.prepareStatement(query);
		statement.setString(1, test.getStudent().getNo());
		statement.setString(2, test.getSubject().getCd());
		statement.setString(3, test.getSchool().getCd());
		statement.setInt(4, test.getPoint());
		statement.setInt(5, test.getNo());
		statement.setString(6, test.getClassNum());

		int rowsInserted = statement.executeUpdate();
		if (rowsInserted > 0) {
			success = true;
		}
	} catch (Exception e) {
		throw e;
	} finally {
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
	return success;
}

}