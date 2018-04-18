<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
	<title>TeamOneLMS</title>
	<link rel="stylesheet" type="text/css" href="style.css">
	<link href="https://fonts.googleapis.com/css?family=Josefin+Sans" rel="stylesheet"> 
</head>
<body>

<form method="post" id="loginForm" action="loginServlet">
	<p>Username:</p>
	<input type="text/html" name="username" required>
	<br>
	<p>Password:</p>
	<input type="text/html" name="password" required>
	<c:if test="${not empty error}" >
	    <br><h5>${error}</h5>
	</c:if>
	<br>
	<br>
	<input id="button" type="submit" value="Login">
	<br>
	<a href="register.jsp"><p>Create account</p></a>
</form>



</body>
</html>