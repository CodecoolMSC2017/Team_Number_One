<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>Your Result</title>
        <link rel="stylesheet" type="text/css" href="style.css">
        <link href="https://fonts.googleapis.com/css?family=Josefin+Sans" rel="stylesheet">
    </head>
    <body>
    <jsp:include page="sideBar.jsp"/>
    <div class="content">
        <p>Dear <c:out value = "${userName}"/></p>
        <p>Your result on <c:out value = "${assignTitle}"/> is</p>
        <p><c:out value = "${result}"/></p>
    </div>
    </body>
</html>