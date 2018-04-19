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
<jsp:include page="sideBar.jsp"/>

    <div class="content">
    <script type="text/javascript">
        var wrongName = <%= (Boolean)request.getAttribute("isSuccess") %> ;
    if (wrongName) {
        window.alert("Your entry has been saved!");
        }
    </script>

    <% User user = (User)session.getAttribute("user"); %>

    <h1 id="welcomeText">Welcome ${user.name}!</h1>
    <div id="choices">
        <c:forEach items="${pageList}" var="page">

            <form method="get" id="${page.getId()}" action="curriculum">
                <input type="hidden" name="id" value="${page.id}">
                <input type="submit" value="${page.getTitle()}">
                <br>
            </form>
            <c:if test="${page.isPublished()}">
                <form method="post" id="${page.getId()}" action="publish">
                <c:if test="${user.role eq 'Mentor'}">
                    <input type="hidden" name="id" value="${page.id}">
                    <button style="background-color:green" id="publishButton" type="submit">Publish/Unpublish</button>

                </c:if>
            </c:if>
            <c:if test="${!page.isPublished()}">
                        <form method="post" id="${page.getId()}" action="publish">
                        <c:if test="${user.role eq 'Mentor'}">
                            <input type="hidden" name="id" value="${page.id}">
                            <button style="background-color:red" id="publishButton" type="submit">Publish/Unpublish</button>
                        </c:if>
            </c:if>

            </form>
        </c:forEach>

</div>
</body>
</html>
