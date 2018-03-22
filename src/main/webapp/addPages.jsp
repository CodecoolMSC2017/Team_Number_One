<html>
    <head>
        <title>add_pages</title>
        <link rel="stylesheet" type="text/css" href="style.css">
        <link href="https://fonts.googleapis.com/css?family=Josefin+Sans" rel="stylesheet">

    </head>
    <body>

        <form id="addForm" action="addPages" method="POST">
            <p>Title:</p>
            <input type="text/html" name="title" id="title" required>
            <br>
            <p>Content:</p>
            <input type="text/html" name="textcontent" id="textcontent" required>
            <br>
            <br>
            <input type="submit" value="Submit">
            <br>
            <a id="registerLink" href="curriculum.jsp"><p>Back to the curriculum</p></a>
        </form>
        
    </body>
</html>