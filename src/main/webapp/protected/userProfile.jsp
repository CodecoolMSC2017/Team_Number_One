<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>User Profile</title>
    <link rel="stylesheet" type="text/css" href="style.css">
    <link href="https://fonts.googleapis.com/css?family=Josefin+Sans" rel="stylesheet">
</head>
<body>
<h1 id="welcomeText">Welcome ${user.name}!</h1>
<br>
<form type="submit" method="POST" action="updateProfile">
    <table border="1">
        <tr>
            <td>User Name</td>
            <c:choose>
                <c:when test="${user.role == 'Mentor' || user.uniqueId == profile.uniqueId}">
                    <td><input type="text" id="userName" name="userName" value="${profile.name}"></td>
                </c:when>
                <c:otherwise>
                    <td>${profile.name}</td>
                </c:otherwise>
            </c:choose>
        </tr>
        <tr>
            <td>User Email</td>
            <td>${profile.email}</td>
        </tr>
        <tr>
            <td>User Role</td>
            <c:choose>
                <c:when test="${user.role eq 'Mentor'}">
                    <td>
                        <select id="userRole" name="userRole">
                            <option selected>${profile.role}</option>
                            <option>Student</option>
                            <option>Mentor</option>
                        </select>
                    </td>
                </c:when>
                <c:otherwise>
                    <td>${profile.role}</td>
                </c:otherwise>
            </c:choose>
        </tr>
    </table>
    <input type="hidden" name="id" value="${profile.uniqueId}">
    <input type="submit" value="Save Changes">
</form>
<c:if test="${profile.uniqueId == user.uniqueId}">
    <form action="updateProfile" method="GET" id="score">
        <input type="submit" value="My Scores">
    </form>
</c:if>
<form action="curriculum" method="GET">
    <input type="submit" name="showUsers" value="Back to User List">
</form>
</body>
</html>