<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <Title>Submissions Page</Title>
        <link rel="stylesheet" type="text/css" href="style.css">
        <link href="https://fonts.googleapis.com/css?family=Josefin+Sans" rel="stylesheet">
    </head>
    <body>
    <jsp:include page="sideBar.jsp"/>
    <div class="content">
            <table border="2" id="userTable">
                <tr>
                    <td>Student</td>
                    <td>Assignment Title</td>
                    <td>Score</td>
                    <td>Evaluate Submission</td>
                </tr>
                <c:forEach var="result" items="${results}">
                    <tr>
                        <td><c:out value="${result.user.name}"/></td>
                        <td><c:out value="${result.assignmentPageTitle}"/></td>
                        <td><c:out value="${result.score}"/></td>
                        <td>
                            <form method="POST" action="evaluation">
                                <c:set var="result" value="${result}" scope="session"/>
                                <input type="submit" value="Submit">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <br>
            <br>

    </div>
    </body>
</html>