<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
	<c:param name="title" value="得点管理システム" />

	<c:param name="content">
		<h2>科目情報削除確認</h2>
		<form action="SubjectDeleteExecute.action" method="post"
			onsubmit="return confirmDelete()">
			<p>
				<label class="form-label">「${subject.getName()}(${subject.getCd()})」を削除してもよろしいですか</label>
				<input type="hidden" name="cd" value="${subject.getCd()}" />
			</p>

			<input type="submit" value="削除" />
		</form>
		<div style="margin-top: 20px;">
			<a href="SubjectList.action" class="nav-item">戻る</a>
		</div>
	</c:param>
</c:import>
