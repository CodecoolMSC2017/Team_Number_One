<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <Title><c:out value = "${ap.title}"/></Title>
        <link rel="stylesheet" type="text/css" href="style.css">
            <link href="https://fonts.googleapis.com/css?family=Josefin+Sans" rel="stylesheet">
    </head>
    <body>
        <h1><c:out value = "${ap.title}"/></h1>
        <form action="check-answers" method="post">
            <input type="hidden" name="id" value="${ap.id}">
            <c:forEach var="q" items="${ap.listOfQuestions}">
                <p>Question:<br><c:out value="${q.question}"/></p>
                <p>Answer:<br></p>
                <br>
                <input type="text/html" name="${q.answer.answer}">

            </c:forEach>
            <br>
            <input type="submit" value="Submit">
        </form>
    </body>
</html>