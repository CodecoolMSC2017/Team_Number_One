<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Curriculum</title>
</head>
<body>
<h1>Welcome ${name.text}!</h1>
<c:if test="${pageList.size() > 0}">
    <c:forEach items="${pageList}" var="page">
        <form method="get" id="${page.getId()}" action="curriculum">
            <input type="submit" value="${page.getTitle()}">
            <br>
        </form>
    </c:forEach>
</c:if>
<form method="get" id="newText" action="curriculum" name="id">
    <input type="submit" value="Create New Text Page">
    <br>
</form>
<form method="get" id="newAssignment" action="curriculum">
    <input type="submit" value="Create New Assignment">
    <br>
</form>
<a href="index.html">Log Out</a>
</body>
</html>
