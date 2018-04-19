<html>
<head>
    <title>TeamOneLMS</title>
    <link rel="stylesheet" type="text/css" href="style.css">
    <link href="https://fonts.googleapis.com/css?family=Josefin+Sans" rel="stylesheet">
</head>
<body>
<jsp:include page="sideBar.jsp"/>
    <div class="content">
        <div id="backdrop">

            <div id="textPageTitleDiv">
                <p>Title:</p>
                <textarea name="titleTextArea" id="titleTextArea" form="textPageForm">
                </textarea>
            </div>

            <div id="textPageContentDiv">
                <p>Content:</p>
                <textarea cols="100" name="contentTextArea" id="contentTextArea" form="textPageForm">
                </textarea>
            </div>

            <form method="post" id="textPageForm">
                <input id="submitTextPageButton" type="submit" value="Submit">
            </form>
        </div>
    </div>



</body>
</html>