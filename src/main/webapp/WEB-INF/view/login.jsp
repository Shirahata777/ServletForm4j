<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン画面</title>
</head>
<body>

	<main>
		<h1>ログイン画面</h1>

		<p>アクセス先のページを見るにはユーザー認証が必要です</p>

		<form method="POST" action="j_security_check" name="loginform">
			<table>
				<tr>
					<td>ユーザー名</td>
					<td><input type="text" name="j_username" size="32"></td>
				</tr>
				<tr>
					<td>パスワード</td>
					<td><input type="password" name="j_password" size="32"></td>
				</tr>
				<tr>
					<td><input type="submit" value="login"></td>
					<td><input type="reset" value="reset"></td>
				</tr>
			</table>
		</form>
	</main>

</body>
</html>