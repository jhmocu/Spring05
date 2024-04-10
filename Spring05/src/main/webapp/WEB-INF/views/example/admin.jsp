<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin</title>
</head>
<body>    
	<h2>ADMIN 권한만 접근 가능한 admin 페이지</h2>
	
	<h2>회원 정보</h2>
	<p>principal : <sec:authentication property="principal"/></p><%-- 
	<p>회원 아이디 : <sec:authentication property="principal.userName" /></p>
	<p>회원 이름 : <sec:authentication property="principal.member.memberName" /></p>
	<p>회원 권한 : <sec:authentication property="printcipal."/></p> --%>
	
	
	<form action="../access/logout" method="POST">
		<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
		<input type="submit" value="로그아웃">
	</form>
</body>
</html>