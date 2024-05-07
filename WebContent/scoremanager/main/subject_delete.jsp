<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
	<c:param name="title" value="得点管理システム" />

	<c:param name="content">
		<style>
input[type="submit"] {
	background-color: #dc143c;
	color: white;
	border: none;
	padding: 7px 20px ;
	font-size: 16px;
	border-radius: 5px;
}
</style>
		<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">科目情報削除確認</h2>
		<form action="SubjectDeleteExecute.action" method="post"
			onsubmit="return confirmDelete()">
			<p>
				<label class="form-label">「${subject.getName()}(${subject.getCd()})」を削除してもよろしいですか</label>
				<input type="hidden" name="cd" value="${subject.getCd()}" />
			</p>

			<input type="submit" value="削除" />
		</form>
		<br>
			<br>
		<div style="margin-top: 20px;">
			<a href="SubjectList.action" class="nav-item">戻る</a>
		</div>
	</c:param>
</c:import>
