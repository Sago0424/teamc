<%-- 学生情報削除JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
	<c:param name="title" value="得点管理システム" />
	<c:param name="scripts"></c:param>
	<c:param name="content">
		<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">学生情報削除</h2>
		<form action="StudentDeleteExecute.action" method="post"
			onsubmit="return confirm('この学生を削除しますか？');">
			<p>学生番号: ${student.getNo()}</p>
			<input type="hidden" name="no" value="${student.getNo()}" />
			<p>氏名: ${student.getName()}</p>
			<p>入学年度: ${student.getEntYear()}</p>
			<p>クラス: ${student.getClassNum()}</p>
			<p>在学中: ${student.isAttend() ? '〇' : '×'}</p>
			<input type="submit" value="削除"
				style="background-color: #dc143c; color: white; border: none; padding: 7px 20px; font-size: 16px; border-radius: 5px;">
			<div style="margin-top: 20px;">
				<a href="StudentList.action" class="nav-item">戻る</a>
			</div>
		</form>
	</c:param>
</c:import>
