<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
    <c:param name="title" value="テスト情報管理" />
    <c:param name="scripts"></c:param>
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">テスト情報管理</h2>

            <div class="my-2 text-end px-4">
                <a href="TestCreate.action">新規登録</a>
            </div>

            <c:choose>
                <c:when test="${not empty tests}">
                    <div>検索結果：${tests.size()}件</div>
                    <table class="table table-hover">
                        <tr>
                            <th>学生番号</th>
                            <th>科目コード</th>
                            <th>学校コード</th>
                            <th>テスト番号</th>
                            <th>点数</th>
                            <th>クラス番号</th>
                            <th></th>
                            <th></th>
                        </tr>
                        <c:forEach var="test" items="${tests}">
                            <tr>
                                <td>${test.studentNo}</td>
                                <td>${test.subjectCd}</td>
                                <td>${test.schoolCd}</td>
                                <td>${test.no}</td>
                                <td>${test.point}</td>
                                <td>${test.classNum}</td>
                                <td><a href="#">変更</a></td>
                                <td><a href="#">削除</a></td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:when>
                <c:otherwise>
                    <div>テスト情報が存在しませんでした</div>
                </c:otherwise>
            </c:choose>
        </section>
    </c:param>
</c:import>
