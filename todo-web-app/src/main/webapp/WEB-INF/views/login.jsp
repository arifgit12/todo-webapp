<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Learning JSP</title>
	</head>
	<body>
		<p><font color="red">${errorMessage}</font></p>
		<form action="/login.do" method="POST">
			Name : <input name="name" type="text" />
			Password : <input name="password" type="password" />
			<input type="submit" />
		</form>
	</body>
</html>