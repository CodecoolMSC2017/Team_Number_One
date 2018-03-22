<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Curriculum</title>
    <link rel="stylesheet" type="text/css" href="style.css">
    <link href="https://fonts.googleapis.com/css?family=Josefin+Sans" rel="stylesheet">
</head>
<body>
<h1 id="welcomeText">Welcome ${name.text}!</h1>
<c:if test="${pageList.size() > 0}">
    <c:forEach items="${pageList}" var="page">
        <form method="get" id="${page.getId()}" action="curriculum">
            <input type="submit" value="${page.getTitle()}">
            <br>
        </form>
    </c:forEach>
</c:if>
<div id="choices">
    <form method="get" id="newText" action="curriculum" name="id">
        <input type="submit" value="Create New Text Page">
        <br>
    </form>
    <form method="get" id="newAssignment" action="curriculum">
        <input type="submit" value="Create New Assignment">
        <br>
    </form>
</div>
<a id="logout" href="index.html">Log Out</a>
</body>
</html>
