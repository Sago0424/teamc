<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
<c:param name="title">
        得点管理システム
</c:param>
<c:param name="scripts"></c:param>
<c:param name="content">
<section class="me-4">
<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">成績一覧（学生）</h2>
<div style="padding: 10px; margin-bottom: 10px; border: 1px solid #333333; border-radius: 10px; border-color: #696969;">
<!-- 検索フォーム -->
<form action="SubjectTestList.action" method="get">
<div class="row vorder mx-3 mb-3 py-2 align-items-center rounded" id="filter">
<!-- ここから科目別参照フォーム -->
<div class="col-3">
<p>科目情報</p>
</div>
<div class="col-2">
<label class="form-label" for="test-f1-select">入学年度</label>
<select class="form-select" id="test-f1-select" name="f1">
<option value="0">----</option>
<c:forEach var="year" items="${ent_year_set}">
<option value="${year}" <c:if test="${year==f1}">selected</c:if>>${year}</option>
</c:forEach>
</select>
</div>
<div class="col-2">
<label class="form-label" for="test-f2-select">クラス</label>
<select class="form-select" id="test-f2-select" name="f2">
<option value="0">----</option>
<c:forEach var="num" items="${class_num_set}">
<option value="${num}" <c:if test="${num==f2}">selected</c:if>>${num}</option>
</c:forEach>
</select>
</div>
<div class="col-3">
<label class="form-label" for="test-f3-select">科目</label>
<select class="form-select" id="test-f3-select" name="f3">
<option value="0">----</option>
<c:forEach var="subject" items="${subject_set}">
<option value="${subject.cd}" <c:if test="${subject.cd==f3}">selected</c:if>>${subject.name}</option>
</c:forEach>
</select>
</div>
<br>
<div class="col-2 text-center">
<button class="btn btn-secondary" id="filter-button">検索</button>
</div>
<br>
</div>
</form>
<div style="border: 1px solid; border-color: #c0c0c0;"></div>
<!-- 検索フォーム -->
<form action="StudentTestList.action" method="get">
<div class="row vorder mx-3 mb-3 py-2 align-items-center rounded" id="filter">
<!-- ここから学生番号別参照フォーム -->
<div class="col-3">
<p>学生情報</p>
</div>
<div class="col-5">
<p>学生番号</p>
<input type="text" name="f4" required placeholder="学生番号を入力してください" value="${f4}">
</div>
<br>
<div class="col-2 text-center">
<button class="btn btn-secondary" id="filter-button">検索</button>
</div>
</div>
</form>
</div>
<div>氏名: ${student.name} (${student.no})</div>
<c:choose>
<c:when test="${not empty stulist}">
<table class="table table-hover">
<tr>
<th>科目名</th>
<th>科目コード</th>
<th>回数</th>
<th>点数</th>
</tr>
<c:forEach var="stu" items="${stulist}">
<tr>
<td>${stu.name}</td>
<td>${stu.cd}</td>
<td>${stu.no}</td>
<td>${stu.point}</td>
</tr>
</c:forEach>
</table>
</c:when>
<c:otherwise>
<div>成績情報が存在しませんでした</div>
</c:otherwise>
</c:choose>
</section>
</c:param>
</c:import>