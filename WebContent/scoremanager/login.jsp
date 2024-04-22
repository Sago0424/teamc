<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
<c:param name="title">得点管理システム</c:param>
<c:param name="scripts">
<%@ page import="java.sql.*"%>
<%@ page import="javax.sql.DataSource"%>
<%@ page import="org.h2.jdbcx.JdbcDataSource"%>

		<%
			// H2データベースへの接続情報
					String url = "jdbc:h2:~/teamc"; // データベースのURL
					String user = "sa"; // ユーザー名
					String password = ""; // パスワード

					Connection conn = null;
					try {
						// JDBCドライバのロード
						Class.forName("org.h2.Driver");

						// データベースへの接続
						conn = DriverManager.getConnection(url, user, password);

						// ここでSQLクエリを実行するなどの処理を行う
						// ...

					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (SQLException e) {
						e.printStackTrace();
					} finally {
						// データベース接続のクローズ
						if (conn != null) {
							try {
								conn.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
					}
		%>

	</c:param>
<c:param name="content">
<style>
.login-form {
	width: 300px;
	margin: 0 auto;
	padding: 20px;
	border: 1px solid #ccc;
	border-radius: 5px;
}

.form-group {
	margin-bottom: 10px;
}

.form-group label {
	display: block;
}

.form-group input[type="text"], .form-group input[type="password"] {
	width: 100%;
	padding: 5px;
	border: 1px solid #ccc;
	border-radius: 3px;
}

.form-group input[type="checkbox"] {
	margin-right: 5px;
}

.form-group button:hover {
	background-color: #0056b3;
}
</style>
<script>
			// パスワードの表示/非表示を切り替える関数
			function togglePasswordVisibility() {
				var passwordInput = document.getElementById('password');
				var passwordCheckbox = document.getElementById('chk_d_ps');
				if (passwordCheckbox.checked) {
					passwordInput.type = 'text';
				} else {
					passwordInput.type = 'password';
				}
			}
</script>
<body>
<div class="login-container">
<h2>ログイン</h2>
<form action="LoginExecute.action" method="post">
<p>ユーザー名: <input type="text" name="username"></p>
<p>パスワード: <input type="password" name="password"></p>
<p><input type="submit" value="ログイン"></p>
</form>
</body>
</div>
</body>
</c:param>
</c:import>