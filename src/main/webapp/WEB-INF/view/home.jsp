<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Home</h1>
	<span>${loginMemberId}</span>
	<div>
		<a href="/board/boardList">게시판</a>
	</div>
	<div>
	<c:if test="${loginMemberId == null}">
		<a href="/member/login">로그인</a>
	</c:if>
	</div>
	<div>
	<c:if test="${loginMemberId != null}">
		<a href="/member/logout">로그아웃</a>
	</c:if>
	</div>
</body>
</html>