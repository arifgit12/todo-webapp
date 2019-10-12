<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Todos</title>
		<!-- Bootstrap core CSS -->
		<link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
	</head>
	<body>
		<div>
			<p><font color="red">${errorMessage}</font></p>
    		<form action="/spring-mvc/login" method="POST">
        		Name : <input name="name" type="text" />
        		Password : <input name="password" type="password" /> 
        		<input type="submit" />
    		</form>
		</div>
	</body>
</html>