<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<main>
		<p>フォーム</p>
		<p>お問い合わせ内容を入力してください！</p>

		<form method="post" action="./form">
			<p>
				お名前：<input type="text" name="name">
			</p>
			<p>
				メールアドレス：<input type="email" name="email">
			</p>
			<p>
				お問い合わせ内容：<input type="text" name="content">
			</p>
			<input type="submit" value="送信">
		</form>
		
		<a href="/ServletForm4j/result">お問い合わせ一覧画面へ！</a>
	</main>
</body>
</html>