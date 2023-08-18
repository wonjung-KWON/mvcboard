<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>${boardNo}게시판 수정</h1>
<form action="/board/modifyBoard" method="post">
 <table>
 	<tr>
 		<td>boardTitle</td>
 		<td>
 			<input type="text" name="boardTitle">
 		</td>
 	</tr>
 	<tr>
 		<td>boardContent</td>
 		<td>
 			<input type="text" name="boardContent">
 		</td>
 	</tr>
 </table>
 			<input type="hidden" name="memberId" value="${memberId}">
 			<input type="hidden" name="boardNo" value="${boardNo}">
	<button type="submit">수정</button>
</form>
</body>
</html>