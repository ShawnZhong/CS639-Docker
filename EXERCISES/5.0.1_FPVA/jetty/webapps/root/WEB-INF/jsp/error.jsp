<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Review</title>
		<link rel="stylesheet" href="../../main.css">
	</head>
	<body>
		<h1>Oh no! You've encountered an error</h1>
		<p><%= request.getAttribute("error") %></p>		
	</body>
</html>