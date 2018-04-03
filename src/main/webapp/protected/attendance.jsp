<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import ="com.codecool.web.model.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
   <head>
       <meta charset="UTF-8">
       <meta name="viewport" content="width=device-width, initial-scale=1.0">
       <title>Curriculum</title>
       <link rel="stylesheet" type="text/css" href="style.css">
       <link href="https://fonts.googleapis.com/css?family=Josefin+Sans" rel="stylesheet">
   </head>
   <body>
   <p3>
   <c:forEach items="${userList}" var="users">
        ${users.getName()}
        ${users.fakeDaysSinceRegistered()/users.getAttendance()}<br>
   </c:forEach>
   </p3>

    <form action="curriculum" method="GET">
        <input id="button" type="submit" value="logout" name="logout">
    </form>
    </body>
    </html>