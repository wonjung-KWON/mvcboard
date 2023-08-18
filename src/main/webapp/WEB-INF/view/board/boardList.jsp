<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>게시판</h1>
<a href="/board/addBoard">게시글 입력</a>
<a href="/home">home</a>
<div>
	<c:forEach var="m" items="${localNameList}">
			<a href="/board/boardList?localName=${m.localName}">${m.localName}(${m.cnt}) &nbsp;</a>
	</c:forEach>
</div>

<table>
	<tr>
		<th>localName</th>
		<th>boardTitle</th>
		<th>memberId</th>
		<th>createdate</th>
	</tr>
	<c:forEach var="b" items="${boardList}">
		<tr>
			<td>${b.localName}</td>
			<td>
				<a href="/board/boardOne?boardNo=${b.boardNo}">
					${b.boardTitle}
				</a>
			</td>
			<td>${b.memberId}</td>
			<td>${b.createdate}</td>
			<td>
				<a href="/board/modifyBoard?boardNo=${b.boardNo}">수정</a>
			</td>
			<td>
				<form action="/board/removeBoard" method="post">
					<input type="hidden" name="boardNo" value="${b.boardNo}">
					<input type="hidden" name="memberId" value="${loginMemberId}">
					<button type="submit">삭제</button>
				</form>
			</td>
		</tr>
	</c:forEach>
</table>
<c:if test="${currentPage > 1}">
	<a href="/board/boardList?currentPage=${currentPage - 1}&localName=${localName}">이전</a>
</c:if>
<span>${currentPage}</span>
<c:if test="${lastPage > currentPage}">
	<a href="/board/boardList?currentPage=${currentPage + 1}&localName=${localName}">다음</a>
</c:if>
</body>
</html>