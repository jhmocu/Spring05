<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin</title>
</head>
<body>    
	<h2>ADMIN 권한만 접근 가능한 admin 페이지</h2>
	<form action="../access/logout" method="POST">
		<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
		<input type="submit" value="로그아웃">
	</form>
</body>
</html>