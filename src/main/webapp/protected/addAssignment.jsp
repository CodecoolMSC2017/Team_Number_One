<html>
<head>
    <title>add_pages</title>
    <link rel="stylesheet" type="text/css" href="style.css">
    <link href="https://fonts.googleapis.com/css?family=Josefin+Sans" rel="stylesheet">

</head>
<body>
<form id="addForm" method="POST">
    <p>Assignment title / Max Score:</p>
    <input id="title" type="text/html" name="assignTitle" value="${sessionScope.tmpAssign.title}" required>
    <input id="maxScore" type="text/html" name="maxScore" value="${sessionScope.tmpAssign.maxScore}" required>
    <br>
    <input type="hidden" name="assignPageTheReal" value="${tmpAssign}">
    <c:forEach items="${sessionScope.tmpAssign.listOfQuestions}" var="q">
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
<input type="hidden" id="tempPage" value="${tmpAssign}">
<input type="submit" id="button" value="Add Question" form="addForm" formaction="addquestion">
<br>
<br>

<form id="backToCurriculum" action="backToMain" method="GET">
    <input id="button" type="submit" value="Back to curriculum">
</form>
</body>
</html>