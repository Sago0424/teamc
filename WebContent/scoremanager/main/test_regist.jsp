<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/common/base.jsp">
	<c:param name="title">得点管理システム</c:param>
	<c:param name="scripts"></c:param>
	<c:param name="content">
		<section class="me-4">
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績管理</h2>
			<div class="my-2 text-end px-4"></div>
			<form method="get" action="TestRegist.action">
				<div class="row border mx-3 mb-3 py-2 align-items-center rounded"
					id="filter">
					<div class="col-2">
						<label class="form-label" for="student-f1-select">入学年度 </label> <select
							class="form-select" id="student-f1-select" name="f1">
							<option value="0">--------</option>
							<c:forEach var="year" items="${ent_year_set}">
								<option value="${year}" <c:if test="${year==f1}">selected</c:if>>${year}</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-2">
						<label class="form-label" for="student-f2-select">クラス</label> <select
							class="form-select" id="student-f2-select" name="f2">
							<option value="0">--------</option>
							<c:forEach var="num" items="${class_num_set}">
								<option value="${num}" <c:if test="${num==f2}">selected</c:if>>${num}</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-4">
						<label class="form-label" for="test-f4-select">科目</label> <select
							class="form-select " id="test-f4-select" name="f3">
							<option value="0">--------</option>
							<c:forEach var="subject" items="${subjects_set}">
								<option value="${subject.cd}"
									<c:if test="${subject.cd == f3}">selected</c:if>>${subject.name}</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-2">
						<label class="form-label" for="student-f4-select">回数</label> <select
							class="form-select" id="student-f4-select" name="f5">
							<option value="0">--------</option>
							<option value="1" <c:if test="${'1' == f5}">selected</c:if>>1</option>
							<option value="2" <c:if test="${'2' == f5}">selected</c:if>>2</option>
						</select>
					</div>
					<div class="col-2 text-center">
						<button class="btn btn-secondary" name="button" id="filter-button"
							value="serch">検索</button>
					</div>
					<div class="mt-2 text-warning">${errors.get("f1")}</div>
				</div>
			</form>

			<c:choose>
				<c:when test="${tests.size() > 0}">
					<div>科目：${f3} (${num}回)</div>
					<form method="post" action="TestRegistExecute.action">
						<table class="table table-hover">
							<tr>
								<th>入学年度</th>
								<th>クラス</th>
								<th>学生番号</th>
								<th>氏名</th>
								<th>点数</th>
							</tr>
							<c:forEach var="test" items="${tests}">
								<tr>
									<td>${test.student.entYear}</td>
									<td>${test.classNum}</td>
									<td>${test.student.no}</td>
									<td>${test.student.name}</td>
									<td><input type="text" name="point${test.student.no}"
										value="${test.point}"> <c:if
											test="${test.student.no eq StudentNo}">
											<c:if test="${not empty pointerrors}">
												<c:forEach var="pointerror" items="${pointerrors}">
													<div>
														<span style="color: #ffd9a3;">${pointerror}</span>
													</div>
												</c:forEach>
											</c:if>
										</c:if></td>
								</tr>
							</c:forEach>
						</table>
						<input type="hidden" name="f1" value="${entYear}"> <input
							type="hidden" name="f2" value="${classnum}"> <input
							type="hidden" name="f3" value="${subject}"> <input
							type="hidden" name="f5" value="${num}"> <input
							type="submit" value="登録して終了"
							style="background-color: #6a737b; color: white; border: none; border-radius: 10px; padding: 8px 15px; margin-bottom: 20px;">
					</form>
				</c:when>
				<c:when test="${tests.size() <= 0}">
					<div>科目：${f6.getName()} (${num}回)</div>
					<form method="post" action="TestRegistExecute.action">
						<table class="table table-hover">
							<tr>
								<th>入学年度</th>
								<th>クラス</th>
								<th>学生番号</th>
								<th>氏名</th>
								<th>点数</th>
							</tr>
							<c:forEach var="student" items="${students}">
								<tr>
									<td>${student.entYear}</td>
									<td>${student.classNum}</td>
									<td>${student.no}</td>
									<td>${student.name}</td>
									<td><input type="text" name="point${student.no}">
										<c:if test="${student.no eq StudentNo}">
											<c:if test="${not empty pointerrors}">
												<c:forEach var="pointerror" items="${pointerrors}">
													<div>
														<span style="color: #ffd9a3;">${pointerror}</span>
													</div>
												</c:forEach>
											</c:if>
										</c:if></td>
								</tr>
							</c:forEach>
						</table>
						<input type="hidden" name="f1" value="${entYear}"> <input
							type="hidden" name="f2" value="${classnum}"> <input
							type="hidden" name="f3" value="${subject}"> <input
							type="hidden" name="f5" value="${num}"> <input
							type="submit" value="登録して終了"
							style="background-color: #6a737b; color: white; border: none; border-radius: 10px; padding: 8px 15px; margin-bottom: 20px;">
					</form>
				</c:when>
			</c:choose>
		</section>
	</c:param>
</c:import>