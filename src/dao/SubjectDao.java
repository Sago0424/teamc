package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
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
	public Subject get(String subjectCd, School school) throws Exception {
		Subject subject = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = getConnection();
			statement = connection.prepareStatement("SELECT * FROM SUBJECT WHERE CD = ? AND SCHOOL_CD = ?");
			statement.setString(1, subjectCd);
			statement.setString(2, school.getCd());
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
	public boolean exists(String Cd) throws Exception {
	    // コネクションを確立
	    Connection connection = getConnection();
	    // プリペアードステートメント
	    PreparedStatement statement = null;
	    // リザルトセット
	    ResultSet resultSet = null;

	    try {
	        // プリペアードステートメントにSQL文をセット
	        statement = connection.prepareStatement("SELECT COUNT(*) FROM subject WHERE cd = ?");
	        // プリペアードステートメントに学生番号をバインド
	        statement.setString(1, Cd);
	        // SQL文を実行し、リザルトセットを取得
	        resultSet = statement.executeQuery();

	        // 結果が存在し、かつ1件以上の場合は学生番号が既に存在する
	        if (resultSet.next() && resultSet.getInt(1) > 0) {
	            return true;
	        }
	    } catch (SQLException e) {
	        throw e;
	    } finally {
	        // リソースを解放
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

	    // 学生番号が存在しない場合
	    return false;
	}


}