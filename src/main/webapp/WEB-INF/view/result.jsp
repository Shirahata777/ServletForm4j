<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>お問い合わせ一覧画面</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bulma@0.9.3/css/bulma.min.css">
</head>
<body>
	<main>
		<table class="table">
			<thead>
				<tr>
					<th><abbr title="id">ID</abbr></th>
					<th><abbr title="name">お名前</abbr></th>
					<th><abbr title="email">メールアドレス</abbr></th>
					<th><abbr title="content">お問い合わせ内容</abbr></th>
				</tr>
			</thead>
			<tfoot>
				<tr>
					<th><abbr title="id">ID</abbr></th>
					<th><abbr title="name">お名前</abbr></th>
					<th><abbr title="email">メールアドレス</abbr></th>
					<th><abbr title="content">お問い合わせ内容</abbr></th>
				</tr>
			</tfoot>
			<tbody>
				<%
				ArrayList<ArrayList<String>> formDataList = (ArrayList<ArrayList<String>>) request.getAttribute("formDataList");
				%>
				<%
				for (ArrayList<String> formData : formDataList) {
				%>
				<tr>
					<%
					for (String data : formData) {
					%>

					<th><%=data%></th>


					<%
					}
					%>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
	</main>



</body>
</html>