<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
	<c:param name="title" value="得点管理システム" />
	<c:param name="scripts"></c:param>
	<c:param name="content">
		<style>


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
	width: 100%;
	padding: 5px;
	margin-bottom: 5px;
	border-radius: 4px;

}
</style>
		<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">科目情報変更</h2>
		<form action="SubjectUpdateExecute.action" method="post">
			<p>
				<label class="form-label">科目コード<br>${subject.getCd()}</label> <input
					type="hidden" name="cd" value="${subject.getCd()}" readonly
					style="border: none; border-bottom: 1px solid #ddd;" />

			</p>
			<p>
				<label class="form-label">科目名<br>
				<input type="text" name="name" placeholder="科目名を入力してください"
					value="${subject.getName()}" required maxlength="20" /></label>
			</p>
			<input type="submit" value="変更"
						style="padding: 10px; border: none; border-radius: 4px; cursor: pointer; background-color: #5577ff; color: white; text-align: center; max-width: 130px;">
			<div style="margin-top: 20px;">
				<a href="SubjectList.action" class="nav-item">戻る</a>
			</div>
		</form>
	</c:param>
</c:import>