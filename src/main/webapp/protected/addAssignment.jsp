<html>
    <head>
        <title>add_pages</title>
        <link rel="stylesheet" type="text/css" href="style.css">
        <link href="https://fonts.googleapis.com/css?family=Josefin+Sans" rel="stylesheet">

    </head>
    <body>
        <form id="addForm" method="POST">
            <p>Assignment title  /  Max Score:</p>
            <input id="title" type="text/html" name="assignTitle" required>
            <input id="maxScore" type="text/html" name="maxScore" required>
            <br>
            <p>Question:</p>
            <input id="question" type="text/html" name="question" required>
            <br>
            <p>Answer:</p>
            <input id="answer" type="text/html" name="answer" required>
            <br>
        </form>

        <input id="button" type="submit" value="Submit" form="addForm" formaction="addpage">
        <input type="submit" id="button" value="Add Question" form="addForm" formaction="addquestion">
        <br>
        <br>

        <form id="backToCurriculum" action="backToMain" method="GET">
            <input id="button" type="submit" value="Back to curriculum">
        </form>
    </body>
</html>