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

        <c:forEach items="${pageList}" var="page">

                <form method="get" id="${page.getId()}" action="curriculum">
                    <input id="textPageButton" type="button" value="${page.getTitle()}" onclick="location.href='sub-page?id=${page.getId()}';">
                    <input type="checkbox" id="${page.getId()}">
                    <br>
                </form>

        </c:forEach>


        <form action="curriculum" method="POST">
            <input type="submit" name="addPages" value="Add Content">
        </form>
        <form action="curriculum" method="POST">
            <input type="submit" name="publish" value="Publish/Unpublish pages">
        </form>

</div>
<br>
<form action="curriculum" method="GET">
    <input id="button" type="submit" value="logout" name="logout">
</form>
</body>
</html>
