<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <Title>Submission Page</Title>
        <link rel="stylesheet" type="text/css" href="style.css">
        <link href="https://fonts.googleapis.com/css?family=Josefin+Sans" rel="stylesheet">
    </head>
    <body>
    <jsp:include page="sideBar.jsp"/>
    <div class="content">
        <h1><c:out value = "${assignTitle}"/></h1>
        <h3><c:out value="${userName}"/></h3>
            <c:forEach var="q" items="${map}">
                <p>Question:<br><c:out value="${q.key.question}"/></p>
                <p>Correct Answer:<br></p>
                    <input type="text/html" value="${q.key.answer.answer}" disabled>
                <p>Student Answer:<br></p>
                    <input type="text/html" value="${q.value}" disabled>
            </c:forEach>
            <br>
            <form method="POST" action="listOfSubmissions">
                <c:set var="result" value="${result}" scope="session"/>
                <input type="text/html" name="score" value="${result.score}">
                <input type="submit" value="Submit">
            </form>
    </div>
    </body>
</html>