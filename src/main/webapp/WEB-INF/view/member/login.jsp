<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>로그인</h1>
<a href="/home">home</a>
<form action="/member/login" method="post">
<table>
	<tr>
		<th>아이디</th>
		<td><input type="text" name="memberId"></td>
	</tr>
	<tr>
		<th>비밀번호</th>
		<td><input type="password" name="memberPw"></td>
	</tr>
</table>
<button type="submit">로그인</button>
</form>
</body>
</html>