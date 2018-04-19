<html>
    <head>
        <title>add_pages</title>
        <link rel="stylesheet" type="text/css" href="style.css">
        <link href="https://fonts.googleapis.com/css?family=Josefin+Sans" rel="stylesheet">

    </head>
    <body>
    <jsp:include page="sideBar.jsp"/>
    <div class="content">
        <form id="addForm" action="addpage" method="POST">
            <p>Title:</p>
            <input id="titleTextArea" type="text/html" name="textTitle" id="textTitle" required>
            <br>
        </form>
        <div id="textPageContentDiv">
            <p>Content:</p>
            <textarea cols="100" name="textContent" id="contentTextArea" form="addForm" wrap="soft">
            </textarea>
        </div>

        <input id="button" type="submit" value="Submit" form="addForm">
        <br>
        <br>


    </div>
    </body>
</html>