<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <Title><c:out value = "${ap.title}"></Title>
</head>
<body>
    <h1><c:out value = "${ap.title}"></h1>
    <c:forEach var="q" items="${ap.listOfTasks}">
        <p>Question:<br><c:out value="${q.question}"/></p>
        <p>Answers:<br></p>
        <c:forEach var="a" items="${q.listOfAnswers}">
            <p><c:out value = "${a.answer}"></p>
        </c:forEach>
    </c:forEach>
</body>