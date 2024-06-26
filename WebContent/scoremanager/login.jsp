<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
	<c:param name="title">得点管理システム</c:param>
	<c:param name="scripts"></c:param>
	<c:param name="content">
		<script>
            // パスワードの表示/非表示を切り替える関数
            function togglePassword() {
                var passwordInput = document.getElementById('password');
                var passwordCheckbox = document.getElementById('chk_d_ps');
                if (passwordCheckbox.checked) {
                    passwordInput.type = 'text';
                } else {
                    passwordInput.type = 'password';
                }
            }
</script>
		<body>
			<div class="row border mx-3 mb-3  align-items-center rounded"
				id="fillter">
				<h2 class="h2 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4"
					style="text-align: center;">ログイン</h2>
				<div style="text-align: center;">
					<form action="LoginExecute.action" method="post">
						<div>
							<c:if test="${not empty errors}">
								<div style="color: black; margin-top: 10px;">
									<c:forEach items="${errors}" var="error">
										<p>ログインに失敗しました。IDまたはパスワードが正しくありません。</p>
									</c:forEach>
								</div>
							</c:if>
						</div>
						<div>
							<input type="text" id="id" name="id" placeholder="ID"
								maxlength="20" required
								style="width: 80%; max-width: 600px; height: 50px; margin-bottom: 10px; border-radius: 5px; border: 2px solid silver; box-sizing: border-box;"
								value="${not empty errors ? param.id : '' }">
						</div>
						<div>
							<input type="password" id="password" name="password"
								placeholder="パスワード" maxlength="20" required maxlength="20"
								required
								style="width: 80%; max-width: 600px; height: 50px; margin-bottom: 10px; border-radius: 5px; border: 2px solid silver; box-sizing: border-box;">
						</div>
						<div>
							<input type="checkbox" id="chk_d_ps" onclick="togglePassword()">
							<label for="chk_d_ps">パスワードを表示</label>
						</div>
						<div>
							<input type="submit" name="login" value="ログイン"
								style="margin-top: 10px; margin-bottom: 10px; max-width: 130px; height: 40px; width: 100%; background-color: #5577ff; color: white; text-align: center; border-radius: 5px; border: none;">
						</div>
					</form>
				</div>
			</div>
		</body>
	</c:param>
</c:import>