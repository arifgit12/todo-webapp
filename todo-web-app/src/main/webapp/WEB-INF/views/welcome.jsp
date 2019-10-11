<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Todos</title>
		<!-- Bootstrap core CSS -->
		<link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css"
			rel="stylesheet">
		<style>
			.footer {
				position: absolute;
				bottom: 0;
				width: 100%;
				height: 60px;
				background-color: #f5f5f5;
			}
			
			.footer .container {
				width: auto;
				max-width: 680px;
				padding: 0 15px;
			}
		</style>
	</head>
	<body>
		<nav role="navigation" class="navbar navbar-default">

			<div class="">
				<a href="/" class="navbar-brand">Brand</a>
			</div>
	
			<div class="navbar-collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">Home</a></li>
					<li><a href="/todo.do">Todos</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="/login.do">Login</a></li>
				</ul>
			</div>
	
		</nav>
		<div  class="container">
			<H1>Welcome ${name}</H1>
			<div>
				Your Todos are
				${todos}
			</div>
		</div>
		<footer class="footer">
			<div class="container">
				<p>footer content</p>
			</div>
		</footer>
		<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
		<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	</body>
</html>