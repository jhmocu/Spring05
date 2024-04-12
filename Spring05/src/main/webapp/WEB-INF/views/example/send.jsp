<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="send" method="POST">
		<input type="text" name="title"><br>
		<textarea rows="2" cols="50" name="content"></textarea><br>
		<!-- CSRF 토큰 -->
		<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
		<input type="submit" value="전송">
	</form>

</body>
</html>