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
<c:forEach items="${userList}" var="user">
    <c:if test="${user.getUniqueId() eq 'userID'}">
        <%request.setAttribute("userRole", "${user.getRole()}");%>
        <%System.out.println(pageContext.findAttribute("user").getRole());%>
    </c:if>
</c:forEach>
<h1 id="welcomeText">Welcome ${name.text}!</h1>
<div id="choices">
    <% System.out.println(pageContext.findAttribute("userRole")); %>
    <c:if test="${pageList.size() > 0}">
        <c:forEach items="${pageList}" var="page">
            <c:if test="${userRole eq 'Mentor' || page.isPublished()}">
                <c:if test="${userRole eq 'Mentor'}">
                    <tr>
                        <td><html:checkbox property="select" value="${page.getId()}"/></td>
                    </tr>
                </c:if>
                <form method="get" id="${page.getId()}" action="curriculum">
                    <input type="submit" value="${page.getTitle()}">
                    <br>
                </form>
            </c:if>
        </c:forEach>
    </c:if>
    <c:if test="${userRole eq 'Mentor'}">
        <form method="get" id="newText" action="curriculum">
            <input type="submit" value="Create New Text Page">
            <br>
        </form>
        <form method="get" id="newAssignment" action="curriculum">
            <input type="submit" value="Create New Assignment">
            <br>
        </form>
    </c:if>
</div>
<br>
<a id="logout" href="index.html">Log Out</a>
</body>
</html>
