<%-- 学生情報変更JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
    <c:param name="title" value="得点管理システム - 学生情報変更" />
    <c:param name="scripts"></c:param>
    <c:param name="content">
        <h2>学生情報変更</h2>
        <form action="StudentUpdateExecute.action" method="post">
            <p>学生番号: ${student.getNo()}</p>
            <input type="hidden" name="no" value="${student.getNo()}" />
            <p>氏名: <input type="text" name="name" value="${student.getName()}" required /></p>
            <p>入学年度: ${student.getEntYear()}</p> <!-- 入学年度は表示のみ -->
            <input type="hidden" name="entYear" value="${student.getEntYear()}" /> <!-- 入学年度は送信されないように -->
            <label class="form-label" for="student-f2-select">クラス</label>
            <select class="form-select " id="student-f2-select" name="classNum">
                <c:forEach var="num" items="${class_num_set}">
                    <option value="${num}" <c:if test="${num eq student.getClassNum()}">selected</c:if>>${num}</option>
                </c:forEach>
            </select>
            <p>在学: <input type="checkbox" name="attend" value="true" ${student.isAttend() ? 'checked' : ''} /></p>
            <input type="submit" value="変更する">
        </form>
    </c:param>
</c:import>
