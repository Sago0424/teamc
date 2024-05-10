<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
    <c:param name="title" value="得点管理システム" />
    <c:param name="scripts"></c:param>
    <c:param name="content">
        <style>


            label {
                font-size: 16px;
                line-height: 1.6;
                color: #555;
            }

            input[type="text"], .form-select {
                width: 100%;
                padding: 10px;
                margin-bottom: 20px;
                border-radius: 4px;
                border: 1px solid #ddd;
            }

            input[type="submit"] {
                padding: 10px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                background-color: #5577ff;
                color: white;
                text-align: center;
                max-width: 130px;
            }

            .form-label {
                padding: 10px;
                display: block;
                margin-bottom: 5px;
                color: #666;
            }
        </style>
        <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">学生情報変更</h2>
        <form action="StudentUpdateExecute.action" method="post">
            <label for="entYear">入学年度<br>　${student.getEntYear()}</label>
            <input type="hidden" name="entYear" value="${student.getEntYear()}" />
            <br><br>
            <label for="studentNo">学生番号<br>　${student.getNo()}</label>
            <input type="hidden" name="no" value="${student.getNo()}" />
            <br><br>
            <label for="name">氏名</label>
            <input type="text" id="name" name="name" placeholder="氏名を入力してください" value="${student.getName()}" required />

            <label for="classNum">クラス</label>
            <select class="form-select" id="classNum" name="classNum">
                <c:forEach var="num" items="${class_num_set}">
                    <option value="${num}" <c:if test="${num eq student.getClassNum()}">selected</c:if>>${num}</option>
                </c:forEach>
            </select>

            <label for="attend">在学中:</label>
            <input type="checkbox" id="attend" name="attend" value="true" ${student.isAttend() ? 'checked' : ''} />
            <br><br>
            <input type="submit" value="変更" />
            <div style="margin-top: 20px;">
                <a href="StudentList.action" class="nav-item">戻る</a>
            </div>
        </form>
    </c:param>
</c:import>
