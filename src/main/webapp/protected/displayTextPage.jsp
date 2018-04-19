<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <Title><c:out value = "${tp.title}" /></Title>
        <link rel="stylesheet" type="text/css" href="style.css">
            <link href="https://fonts.googleapis.com/css?family=Josefin+Sans" rel="stylesheet">
    </head>
    <body>
    <jsp:include page="sideBar.jsp"/>
    <div class="content">
        <h1><c:out value = "${tp.title}" /></h1>
        <textarea disabled id="contentTextArea" colum="120" wrap="soft">
            <c:out value = "${tp.textContent}" />
        </textarea>
        <br>

    </div>
    </body>
</html>