<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
	<c:param name="title">得点管理システム</c:param>
	<c:param name="content">
		<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">科目情報登録</h2>
		<p
			style="background-color: #7ebea5; padding: 8px; width: 100%; text-align: center;">
			登録が完了しました</p>
			<br>
			<br>
			<br>
			<br>
		<a href="SubjectCreate.action" class="nav-item"
			style="margin-right: 60px;">戻る</a>
		<a href="SubjectList.action" class="nav-item">科目一覧</a>


	</c:param>
</c:import>
