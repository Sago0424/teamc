<%-- 学生一覧JSP --%>
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

			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績管理</h2>
			<div class="my-2 text-end px-4"></div>

			<form method="get" action="TestRegist.action">
				<div class="row border mx-3 mb-3 py-2 align-items-center rounded"
					id="filter">
					<!-- 入学年度 -->
					<div class="col-2">
						<label class="form-label" for="student-f1-select">入学年度</label> <select
							class="form-select" id="student-f1-select" name="f1">
							<option value="0">--------</option>
							<c:forEach var="year" items="${ent_year_set}">
								<option value="${year}" <c:if test="${year==f1}">selected</c:if>>${year}</option>
							</c:forEach>
						</select>
					</div>
					<!-- クラス -->
					<div class="col-2">
						<label class="form-label" for="student-f2-select">クラス</label> <select
							class="form-select" id="student-f2-select" name="f2">
							<option value="0">--------</option>
							<c:forEach var="num" items="${class_num_set}">
								<option value="${num}" <c:if test="${num==f2}">selected</c:if>>${num}</option>
							</c:forEach>
						</select>
					</div>
					<!-- 科目 -->
					<div class="col-4">
						<label class="form-label" for="student-f3-select">科目</label> <select
							class="form-select" id="student-f3-select" name="f3">
							<option value="0">--------</option>
							<c:forEach var="subject" items="${subjects_set}">
								<option value="${subject.cd}"
									<c:if test="${subject.cd == f3}">selected</c:if>>${subject.name}</option>
							</c:forEach>
						</select>
					</div>
					<!-- 回数 -->
					<div class="col-2">
						<label class="form-label" for="student-f4-select">回数</label> <select
							class="form-select" id="student-f4-select" name="f4">
							<option value="0">--------</option>
							<c:forEach var="test" items="${number_set}">
								<option value="${test}"
									<c:if test="${test == f4}">selected</c:if>>${test}</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-2 text-center">

						<button class="btn btn-secondary" name="button" id="filter-button"
							value="serch">検索</button>
					</div>
					<div class="mt-2 text-warning">${errors.get("f1")}</div>
				</div>
			</form>
<c:if test="${not empty tests}">
                <div>科目：${subjects_set} (${number_set}回)</div>
                <form method="post" action="TestRegistExecute.action">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>入学年度</th>
                                <th>クラス</th>
                                <th>学生番号</th>
                                <th>氏名</th>
                                <th>点数</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${tests}" var="test">
                                <tr>
                                    <td>${student.entYear}</td>
                                    <td>${classNum}</td>
                                    <td>${student.no}</td>
                                    <td>${student.name}</td>
                                    <td>
                                        <input type="text" name="point_${student.no}" value="${point}">
                                        <c:if test="${not empty errors.get('point_' + test.student.no)}">
                                            <div class="text-warning">
                                                ${errors.get('point_' + student.no)}
                                            </div>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <input type="hidden" name="f1" value="${f1}">
                    <input type="hidden" name="f2" value="${f2}">
                    <input type="hidden" name="f3" value="${f3}">
                    <input type="hidden" name="f4" value="${f4}">
                    <button type="submit" class="btn btn-primary">登録して終了</button>
                </form>
            </c:if>
        </section>
    </c:param>
</c:import>