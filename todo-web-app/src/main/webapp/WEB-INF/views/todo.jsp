<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Yahoo!!</title>
	</head>
	<body>
		<H1>Welcome ${name}</H2>
		<div>
			Your Todos are
			<ol>
				<c:forEach items="${todos}" var="todo">
				   <li>${todo.name}</li>
				</c:forEach>
			</ol>
			<p><font color="red">${errorMessage}</font></p>
			<form method="POST" action="/todo.do">
				New Todo : <input name="todo" type="text" />
				<input name="add" type="submit" />
			</form>
		</div>
	</body>
</html>