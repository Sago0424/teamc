<%-- 学生一覧JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="scripts"></c:param>
    <c:param name="content">
        <h2>学生情報登録</h2>
        <form action="StudentCreateExecute.action" method="post">
            <label class="form-label" for="student-f1-select">入学年度 </label>
            <select class="form-select " id="student-f1-select" name="f1" required>
                <option value="0">--------</option>
                <c:forEach var="year" items="${ent_year_set}">
                    <option value="${year}" <c:if test="${year==f1}">selected</c:if>>${year}</option>
                </c:forEach>
            </select>
            <p>学生番号</p>
            <input type="text" required name="no">
            <p>氏名</p>
            <input type="text" required name="name">
            <label class="form-label" for="student-f2-select">クラス</label>
            <select class="form-select " id="student-f2-select" name="f2" required>
                <option value="">選択してください</option>
                <c:forEach var="num" items="${class_num_set}">
                    <option value="${num}" <c:if test="${num==f2}">selected</c:if>>${num}</option>
                </c:forEach>
            </select>
            <p>
                <input type="submit" value="登録して終了">
            </p>
            <a href="StudentList.action" class="btn">戻る</a>
        </form>
    </c:param>
</c:import>
