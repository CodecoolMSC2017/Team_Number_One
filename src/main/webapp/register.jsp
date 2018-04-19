<html>
<head>
	<title>TeamOneLMS</title>
	<link rel="stylesheet" type="text/css" href="style.css">
	<link href="https://fonts.googleapis.com/css?family=Josefin+Sans" rel="stylesheet"> 
</head>
<body>





<form method="post" id="registerForm" action="register">
	<p>E-mail:</p>
	<input type="email" name="email" required>
	<br>

	<p>Username:</p>
	<input type="text/html" name="username" id="username" required>
	<br>
	<p>Password:</p>
	<input type="text/html" name="password" id="password" required>
	<br>
	<br>
	<input id="button" type="submit" value="Register">
	<br>
	<a id="registerLink" href="index.jsp"><p>Back to homepage</p></a>
</form>
    <h5><c:if test="not empty notAvailable">${notAvailable}</c:if></h5>



</body>
</html>