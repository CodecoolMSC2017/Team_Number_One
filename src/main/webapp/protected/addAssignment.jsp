<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import ="com.codecool.web.model.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>add_pages</title>
    <link rel="stylesheet" type="text/css" href="style.css">
    <link href="https://fonts.googleapis.com/css?family=Josefin+Sans" rel="stylesheet">

</head>
<body>
<jsp:include page="sideBar.jsp"/>
<div class="content">
    <form id="addForm" method="POST">
        <p>Assignment title / Max Score:</p>
        <input id="title" type="text/html" name="assignTitle" value="${reqTmpAssign.title}" required>
        <input id="maxScore" type="text/html" name="maxScore" value="${reqTmpAssign.maxScore}" required>
        <br>
        <input type="hidden" name="assignPageTheReal" value="${reqTmpAssign}">
        <c:forEach items="${reqTmpAssign.listOfQuestions}" var="q">
            <p>Question:</p>
            <input type="text/html" name="alreadySubmittedQuestion" value="${q.question}">
            <br>
            <p>Answer:</p>
            <input type="text/html" name="alreadySubmittedAnswer" value="${q.answer}">
            <br>
        </c:forEach>
        <p>Question:</p>
        <input type="text/html" name="question">
        <br>
        <p>Answer:</p>
        <input type="text/html" name="answer">
        <br>
    </form>

    <input id="button" type="submit" value="Submit" form="addForm" formaction="addpage">
    <input type="hidden" id="tempPage" value="${sessTmpAssign}">
    <input type="submit" id="button" value="Add Question" form="addForm" formaction="addquestion">
    <br>
    <br>


</div>
</body>
</html>