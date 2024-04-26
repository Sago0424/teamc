<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
	<c:param name="content">
		<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">メニュー</h2>

		<div class="row mx-2">
			<div
				class="col d-flex align-items-center justify-content-center mx-2 rounded shadow"
				style="height: 10rem; background-color: #dbb;">
				<a href="StudentList.action" style="font-size: 1.5em;">学生管理</a>
			</div>
			<div
				class="col d-flex flex-column align-items-center justify-content-center mx-2 rounded shadow"
				style="height: 10rem; background-color: #bdb;">
				<a style="font-size: 1.5em;">成績管理</a> <a href="TestRegist.action"
					style="font-size: 1.5em;">成績登録</a> <a href="#"
					style="font-size: 1.5em;">成績参照</a>
			</div>
			<div
				class="col d-flex align-items-center justify-content-center mx-2 rounded shadow"
				style="height: 10rem; background-color: #bbd;">
				<a href="SubjectList.action" style="font-size: 1.5em;">科目管理</a>
			</div>
		</div>
	</c:param>
</c:import>
