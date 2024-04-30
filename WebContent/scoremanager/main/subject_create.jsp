<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
	<c:param name="title" value="得点管理システム" />
	<c:param name="scripts"></c:param>
	<c:param name="content">
		<div style="background-color:; margin: -20px; padding: 20px;">
			<h2 style="text-align:; color: #4a4a4a;">学生情報登録</h2>
			<form action="SubjectCreateExecute.action" method="post"
				style="max-width: 600px;">
				<div style="margin-bottom: 20px;">
					<label for="subject-f1-select"
						style="display: block; margin-bottom: 5px;">科目コード</label> <input
						type="text" id="subject-f1-select" name="cd"
						placeholder="科目コードを入力してください" required
						style="width: 100%; padding: 10px; border-radius: 5px; border: 1px solid #ccc;">
				</div>
				<div style="margin-bottom: 20px;">
					<label for="name" style="display: block; margin-bottom: 5px;">科目名</label>
					<input type="text" id="name" name="name" placeholder="科目名を入力してください"
						required
						style="width: 100%; padding: 10px; border-radius: 5px; border: 1px solid #ccc;">
				</div>
				<div>
					<input type="submit" value="登録して終了"
						style="padding: 10px 20px; border-radius: 5px; border: none; background-color: #444444; color: white; cursor: pointer;">
				</div>
				<div style="margin-top: 20px;">
					<a href="SubjectList.action" class="nav-item">戻る</a>
				</div>
			</form>
		</div>
	</c:param>
</c:import>
