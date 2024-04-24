<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
    <c:param name="title" value="科目登録完了" />
    <c:param name="content">
        <div>
            <h2>科目登録が完了しました。</h2>
            <p>新しい科目がデータベースに登録されました。</p>
            <a href="SubjectList.action" class="nav-item">科目一覧に戻る</a>
        </div>
    </c:param>
</c:import>
