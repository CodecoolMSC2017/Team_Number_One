<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <Title><c:out value = "${tp.title}" /></Title>
    </head>
    <body>
        <h1><c:out value = "${tp.title}" /></h1>
        <p><c:out value = "${tp.textContent}" /></p>
    </body>
</html>