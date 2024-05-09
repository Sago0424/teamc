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
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績一覧（科目）</h2>

			<jsp:include page="test_list_filter.jsp" />

			<!-- ここまで科目別参照フォーム -->
			<div>科目: ${subject.name}</div>
			<c:choose>
				<c:when test="${not empty sublist}">
					<table class="table table-hover">
						<thead>
							<tr>
								<th>入学年度</th>
								<th>クラス</th>
								<th>学生番号</th>
								<th>氏名</th>
								<th>１回</th>
								<th>２回</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="sub" items="${sublist}">
								<tr>
									<td>${sub.entYear}</td>
									<td>${sub.classNum}</td>
									<td>${sub.no}</td>
									<td>${sub.name}</td>
									<td>${sub.point1 == null or sub.point1 eq '' ? '-' : sub.point1}</td>
									<td>${sub.point2 == null or sub.point2 eq '' ? '-' : sub.point2}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:when>
				<c:otherwise>
					<div>学生情報が存在しませんでした</div>
				</c:otherwise>
			</c:choose>
		</section>
	</c:param>
</c:import>