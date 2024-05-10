<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
	<c:param name="title">得点管理システム</c:param>
	<c:param name="scripts">
		<script>
			function validateForm() {
				var selectedYear = document.getElementById("student-f1-select").value;
				if (selectedYear === "0") {
					document.getElementById("error-message").style.display = "block";
					return false;
				} else {
					document.getElementById("error-message").style.display = "none";
					return true;
				}
			}
		</script>
	</c:param>
	<c:param name="content">
		<div style="background-color:; margin: -20px; padding: 20px;">
			<h2 style="text-align:; color: #4a4a4a;">学生情報登録</h2>
			<form action="StudentCreateExecute.action" method="post"
				style="max-width: 600px;" onsubmit="return validateForm();">
				<div style="margin-bottom: 20px;">
					<label for="student-f1-select"
						style="display: block; margin-bottom: 5px;">入学年度</label> <select
						id="student-f1-select" name="f1"
						style="width: 100%; padding: 10px; border-radius: 5px; border: 1px solid #ccc;">
						<option value="0">--------</option>
						<c:forEach var="year" items="${ent_year_set}">
							<option value="${year}" ${year == f1 ? 'selected' : ''}>${year}</option>
						</c:forEach>
					</select>
				</div>
				<!-- エラーメッセージの追加 -->
				<div id="error-message"
					style="display: none; color: red; margin-bottom: 10px;">入学年度を選択してください。</div>
				<div style="margin-bottom: 20px;">
					<label for="no" style="display: block; margin-bottom: 5px;">学生番号</label>
					<input type="text" id="no" name="no" placeholder="学生番号を入力してください"
						required
						style="width: 100%; padding: 10px; border-radius: 5px; border: 1px solid #ccc;">
				</div>
				<div style="margin-bottom: 20px;">
					<label for="name" style="display: block; margin-bottom: 5px;">氏名</label>
					<input type="text" id="name" name="name" placeholder="氏名を入力してください"
						required
						style="width: 100%; padding: 10px; border-radius: 5px; border: 1px solid #ccc;">
				</div>
				<div style="margin-bottom: 20px;">
					<label for="student-f2-select"
						style="display: block; margin-bottom: 5px;">クラス</label> <select
						id="student-f2-select" name="f2"
						style="width: 100%; padding: 10px; border-radius: 5px; border: 1px solid #ccc;">
						<c:forEach var="num" items="${class_num_set}">
							<option value="${num}" ${num == f2 ? 'selected' : ''}>${num}</option>
						</c:forEach>
					</select>
				</div>
				<div>
					<input type="submit" value="登録して終了"
						style="padding: 10px 20px; border-radius: 5px; border: none; background-color: #444444; color: white; cursor: pointer;">
				</div>
				<div style="margin-top: 20px;">
					<a href="StudentList.action" class="nav-item">戻る</a>
				</div>
			</form>
		</div>
	</c:param>
</c:import>