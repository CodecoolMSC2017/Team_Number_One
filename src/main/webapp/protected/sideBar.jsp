<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import ="com.codecool.web.model.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
<link rel="stylesheet" type="text/css" href="sidebarcss.css">
    <link href="https://fonts.googleapis.com/css?family=Josefin+Sans" rel="stylesheet">
</head>
<body>

	<div class="sideBar">
		<form action="backToMain" method="get">
			<input id="firstButton" type="submit" value="Curriculum">
		</form>
		<form action="curriculum" method="get">
			<input type="submit" name="showUsers" value="User List">
		</form>
		<c:if test="${user.role eq 'Mentor'}">
            <form action="curriculum" method="post">
                <input type="submit" name="addPages" value="Add Assignment">
            </form>
            <form action="curriculum" method="post">
                <input type="submit" name="addPages" value="Add Text Page">
            </form>
            <form action="listOfSubmissions" method="get">
                <input type="submit" name="submission" value="Submissions">
            </form>
            <form action="curriculum" method="get">
                <input type="submit" name="attendance" value="Attendance">
            </form>
        </c:if>
		<form action="loginServlet" method="get">
			<input type="submit" name="logout" value="Log Out">
		</form>
	</div>


</body>
</html>
