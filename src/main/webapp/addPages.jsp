<html>
    <head>
        <title>add_pages</title>
        <link rel="stylesheet" type="text/css" href="style.css">
        <link href="https://fonts.googleapis.com/css?family=Josefin+Sans" rel="stylesheet">

    </head>
    <body>
        <form id="addForm" action="addpage" method="POST">
            <p>Title:</p>
            <input type="text/html" name="textTitle" id="textTitle" required>
            <br>
            <p>Content:</p>
            <input type="text/html" name="textContent" id="textContent" required>
            <br>
            <br>
            <input type="submit" value="Submit">
        </form>
        <br>
        <form id="addForm" action="addpage" method="POST">
            <br>
            <p>Title:</p>
            <br>
            <input type="text" name="assTitle" id="assTitle" required>
            <br>
            <br>
            <input type="submit" value="Submit">
        </form>


        <form id="backToCurriculum" action="addpage" method="GET">
        <br>
        <br>
            <input type="submit" value="Back to curriculum">
        </form>
        
    </body>
</html>