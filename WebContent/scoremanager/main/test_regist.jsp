<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>成績登録</title>
</head>
<body>
    <h2>画面タイトル</h2>
    <form action="TestRegistExecuteAction" method="post">
        <select name="f1">
            <!-- サーバーサイドで生成される入学年度のオプション -->
        </select>
        <select name="f2">
            <!-- サーバーサイドで生成されるクラスのオプション -->
        </select>
        <select name="f3">
            <!-- サーバーサイドで生成される科目のオプション -->
        </select>
        <select name="f4">
            <!-- サーバーサイドで生成される回目のオプション -->
        </select>
        <button type="submit">検索</button>
    </form>
    <!-- 成績一覧表示テーブル -->
</body>
</html>
