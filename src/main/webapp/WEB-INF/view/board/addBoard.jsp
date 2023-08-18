<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>게시글 추가</h1>
	<form action="/board/addBoard" method="post" enctype="multipart/form-data">
		<table>
			<tr>
				<td>localName</td>
				<td><input type="text" name="localName" required="required"></td>
			</tr>
			<tr>
				<td>boardTitle</td>
				<td><input type="text" name="boardTitle" required="required"></td>
			</tr>
			<tr>
				<td>boardContent</td>
				<td><input type="text" name="boardContent" required="required"></td>
			</tr>
			<tr>
				<td>memberId</td>
				<td><input type="text" name="memberId" required="required"></td>
			</tr>
			<tr>
				<td>파일 업로드</td>
				<td><input type="file" name="multipartFile" multiple="multiple"></td>
			</tr>
		</table>
			<button type="submit">추가</button>
	</form>
</body>
</html>