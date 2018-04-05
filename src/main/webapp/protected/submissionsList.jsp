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
        <table border="2">
            <tr>
                <td>Student</td>
                <td>Assignment Title</td>
                <td>Evaluate Submission</td>
            </tr>
            <c:forEach var="student" items="${students}">
                <c:forEach var="assignId" items="${student.getAssignIds()}">
                    <tr>
                        <td><c:out value="${student.name}"></td>
                        <td><c:out value="${assignId}"></td>
                        <td>
                            <form method="GET" action="evaluation">
                                <input type="hidden" name="studentName" value="${student.name}">
                                <input type="submit" value="Submit">
                            </form>
                        </td>
                    </tr>
            </c:forEach>
        </table>
    </body>
</html>