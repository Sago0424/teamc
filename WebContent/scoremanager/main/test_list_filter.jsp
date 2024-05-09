<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
.info-heading {
	text-align: left; /* テキストを左寄せにする */
	margin: 30px auto 0; /* 上に20px、水平方向に中央に配置する */
	width: fit-content; /* コンテンツに合わせて幅を調整する */
}

.info-heading2 {
	text-align: left; /* テキストを左寄せにする */
	margin: 35px auto 0; /* 上に20px、水平方向に中央に配置する */
	width: fit-content; /* コンテンツに合わせて幅を調整する */
}

.btn-center {
	display: block; /* ボタンをブロック要素として表示する */
	margin: 20px auto; /* 水平方向に中央に配置する */
}

.btn-center2 {
	display: block; /* ボタンをブロック要素として表示する */
	margin: 35px auto 0; /* 水平方向に中央に配置する */
}

.btn-left {
	margin-right: 10px; /* 右側にマージンを追加して左に動かす */
	margin-left: -70px; /* 左側のマージンをリセット */
}
</style>
<section class="me-4">
	<div
		style="padding: 10px; margin-bottom: 10px; border: 3px solid #333333; border-radius: 5px; border-color: #696969;">
		<!-- 検索フォーム、 -->
		<form action="SubjectTestList.action" method="get">
			<div class="row vorder mx-3 mb-3 py-2 align-itemscenter rounded"
				id="filter">
				<!-- ここから科目別参照フォーム -->
				<div class="col-3">
					<p class="info-heading">科目情報</p>
				</div>
				<div class="col-2">
					<label class="form-label" for="test-f1-select">入学年度</label> <select
						class="form-select" id="test-f1-select" name="f1">
						<option value="0">----</option>
						<c:forEach var="year" items="${ent_year_set}">
							<!-- 現在のyearと選択されていたf1が一致していた場合selectedを記述 -->
							<option value="${year}" <c:if test="${year==f1}">selected</c:if>>${year}</option>
						</c:forEach>
					</select>
				</div>

				<div class="col-2">
					<label class="form-label" for="test-f2-select">クラス</label> <select
						class="form-select" id="test-f2-select" name="f2">
						<option value="0">----</option>
						<c:forEach var="num" items="${class_num_set}">
							<%-- 現在のnumと選択されていたf2が一致していた場合selectedを記述 --%>
							<option value="${num}" <c:if test="${num==f2}">selected</c:if>>${num}</option>
						</c:forEach>
					</select>
				</div>

				<div class="col-3">
					<label class="form-label" for="test-f3-select">科目</label> <select
						class="form-select" id="test-f3-select" name="f3">
						<option value="0">----</option>
						<c:forEach var="subject" items="${subject_set}">
							<%-- 現在のsubjectと選択されていたf3が一致していた場合selectedを記述 --%>
							<option value="${subject.name}"
								<c:if test="${subject.name==f3}">selected</c:if>>${subject.name}</option>
						</c:forEach>
					</select>
				</div>
				<br>
				<div class="col-2 text-center">
					<button class="btn btn-secondary btn-center" id="filter-button">検索</button>
				</div>
				<br>
				<!-- 条件が三つ指定されてない場合のエラーメッセージ -->
				<div style="color: #ffd700;">
					<c:if test="${not empty error}">${error}</c:if>
				</div>
			</div>
		</form>
		<!-- ここまで科目別参照フォーム -->

		<div style="border: 1px solid; border-color: #c0c0c0;"></div>

		<!-- 検索フォーム、 -->
		<form action="StudentTestList.action" method="get">
			<div class="row vorder mx-3 mb-3 py-2 align-itemscenter rounded"
				id="filter">
				<!-- ここから学生番号別参照フォーム -->
				<div class="col-3">
					<p class="info-heading2">学生情報</p>
				</div>
				<div class="col-5">
					<p>学生番号</p>
					<input type="text" name="f4" required placeholder="学生番号を入力してください"
						value="${f4}"
						style="padding: 3px; margin-bottom: 3px; border: 3px solid #333333; border-radius: 5px;">
				</div>
				<br>
				<div class="col-2 text-center">
					<button class="btn btn-secondary btn-center2 btn-left"
						id="filter-button">検索</button>
				</div>
				<!-- ここまで学生番号別参照フォーム -->
			</div>
		</form>
	</div>
</section>