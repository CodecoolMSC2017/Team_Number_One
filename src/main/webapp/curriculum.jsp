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

<script type="text/javascript">
    var wrongName = <%= (Boolean)request.getAttribute("isSuccess") %> ;
if (wrongName) {
    window.alert("Your entry has been saved!");
    }
</script>

<c:forEach items="${userList}" var="user">
    <c:if test="${user.getUniqueId() eq 'userID'}">
        <%request.setAttribute("userRole", "${user.getRole()}");%>
    </c:if>
</c:forEach>
<h1 id="welcomeText">Welcome ${name.text}!</h1>
<div id="choices">
    <c:if test="${pageList.size() > 0}">
        <c:forEach items="${pageList}" var="page">
            <c:if test="${userRole eq 'Mentor' || page.isPublished()}">
                <form method="get" id="${page.getId()}" action="curriculum">
                    <input type="submit" value="${page.getTitle()}">
                    <br>
                </form>
            </c:if>
        </c:forEach>
    </c:if>
    <form action="curriculum" method="POST" >
        <input type="submit" name="newText" value="Show Text Page">
        <br>
    </form>
    <form action="curriculum" method="POST" >
        <input type="submit" name="newAssignment" value="Show Assignment Page">
        <br>
    </form>
    <form action="curriculum" method="POST">
            <input type="submit" name="addPages" value="Add Content">
            <br>
    </form>
</div>
<a id="logout" href="index.html">Log Out</a>
</body>
</html>
