<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
    <head>
        <title>User List</title>
        <link rel="stylesheet" type="text/css" href="style.css">
        <link href="https://fonts.googleapis.com/css?family=Josefin+Sans" rel="stylesheet">
    </head>
    <body>
        <h1 id="welcomeText">Welcome ${user.name}!</h1>
        <br>
        <table border = 1>
            <thead>
                <tr>
                    <th>User Name</th>
                    <th>Email</th>
                    <th>User Role</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${users}" var="currUser">
                    <tr>
                        <td>
                            <form method="get" id="${currUser.uniqueId}" action="userList">
                                <input type="hidden" name="id" value="${currUser.uniqueId}">
                                <input type="submit" value="${currUser.getName()}">
                            </form>
                        </td>
                        <td>${currUser.email}</td>
                        <td>${currUser.role}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <br>
        <form id="backToCurriculum" action="backToMain" method="GET">
            <input id="button" type="submit" value="Back to curriculum">
        </form>
    </body>
</html>