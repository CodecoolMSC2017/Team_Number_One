<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import ="com.codecool.web.model.User" %>
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


<script type="text/javascript">
    var wrongName = <%= (Boolean)request.getAttribute("isSuccess") %> ;
if (wrongName) {
    window.alert("Your entry has been saved!");
    }
</script>

<% User user = (User)session.getAttribute("user"); %>

<h1 id="welcomeText">Welcome ${user.name}!</h1>
<div id="choices">
    <c:forEach items="${sessionScope.pageList}" var="page">
        <c:if test="${user.role eq 'Mentor'}">
            <input type="checkbox" id="${page.getId()}">
        </c:if>
        <form method="get" id="${page.getId()}" action="curriculum">
            <input type="hidden" name="id" value="${page.id}">
            <input type="submit" value="${page.getTitle()}">
            <br>
        </form>
    </c:forEach>
    <c:if test="${user.role eq 'Mentor'}">
        <form action="curriculum" method="POST">
            <input type="submit" name="addPages" value="Add Content">
        </form>
        <form action="curriculum" method="POST">
            <input type="submit" name="publish" value="Publish/Unpublish pages">
        </form>
        <form action="curriculum" method="GET">
            <input type="submit" name="attendance" value="Attendance">
        </form>
    </c:if>
</div>
<br>
<form action="curriculum" method="GET">
    <input id="button" type="submit" value="logout" name="logout">
</form>
</body>
</html>
