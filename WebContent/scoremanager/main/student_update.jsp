<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
	<c:param name="title" value="得点管理システム" />
	<c:param name="scripts"></c:param>
	<c:param name="content">
		<style>
}
form {
	background-color: white;
	padding: 20px;
	border-radius: 5px;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
	max-width: 500px;
	margin: auto;
}

p {
	font-size: 16px;
	line-height: 1.6;
	color: #555;
}

input[type="text"], form-select {
	width: 100%;
	padding: 10px;
	margin-bottom: 20px;
	border-radius: 4px;
	border: 1px solid #ddd;
}

input[type="submit"] {
	padding: 10px;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}

.form-label {
	padding: 10px;
	display: block;
	margin-bottom: 5px;
	color: #666;
}
</style>
		<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">学生情報変更</h2>
		<form action="StudentUpdateExecute.action" method="post">
			<p>
				入学年度<br> ${student.getEntYear()}
			</p>
			<!-- 入学年度は表示のみ -->
			<input type="hidden" name="entYear" value="${student.getEntYear()}" />
			<!-- 入学年度は送信されないように -->
			<p>
				学生番号<br> ${student.getNo()}
			</p>
			<input type="hidden" name="no" value="${student.getNo()}" />
			<p>
				氏名<input type="text" name="name" placeholder="氏名を入力してください"
					value="${student.getName()}" required />
			</p>
			<p>
				クラス<select class="form-select" id="student-f2-select"
					name="classNum">
					<c:forEach var="num" items="${class_num_set}">
						<option value="${num}"
							<c:if test="${num eq student.getClassNum()}">selected</c:if>>${num}</option>
					</c:forEach>
				</select>
			</p>
			<p>
				在学中<input type="checkbox" name="attend" value="true"
					${student.isAttend() ? 'checked' : ''} />
			</p>
			<input type="submit" value="変更"
				style="max-width: 130px; background-color: #5577ff; color: white; text-align: center;">
			<div style="margin-top: 20px;">
				<a href="StudentList.action" class="nav-item">戻る</a>
			</div>
		</form>
	</c:param>
</c:import>