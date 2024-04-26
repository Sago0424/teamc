<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
	<c:param name="title">得点管理システム</c:param>
	<c:param name="content">
		<h2>成績管理</h2>
		<p>登録が完了しました。</p>
		<a href="TestRegist.action" class="nav-item">戻る</a>
		<a href="TestList.action" class="nav-item">成績参照</a>
	</c:param>
</c:import>