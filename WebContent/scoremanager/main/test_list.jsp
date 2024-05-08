<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--taglibディレクティブの記述-->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
	<c:param name="title">
		得点管理システム
</c:param>

	<c:param name="scripts"></c:param>

	<c:param name="content">
		<section class="me-4">
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績参照</h2>

			<jsp:include page="test_list_filter.jsp" />

			<!-- 利用方法案内メッセージ -->
			<div style="color: #00bfff;">
				<p>科目情報を選択または学生情報を入力して検索ボタンをクリックしてください</p>
			</div>
		</section>
	</c:param>
</c:import>