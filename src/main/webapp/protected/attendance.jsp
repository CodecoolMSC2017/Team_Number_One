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
   <form action="attend" method="POST">
        Student name:<br>
        <input list="Students" name="studentName">
            <datalist id="Students">
                <c:forEach items="${userList}" var="studNames">
                    <option value="${studNames.getName()}">
                </c:forEach>
              </datalist>
          <br><br>
        Requested date:<br>
        <input type="date" name="date" id="date"><br><br>
        <input type="submit" value="Submit"><br>
   </form>

   </p3>

   <br><br>
    <table style="width:100%">
      <tr>
        <th>Student name</th>
        <th>Date</th>
        <th>Attended</th>
      </tr>
      <c:forEach items="${matchDate}" var="studUser">
      <tr>
        <td>${studUser.getName()}</td>
        <c:forEach items="${studUser.getAttendance().getAttendacePerDays()}" var="entry">
                    <td>${entry.key}</td>
                    <td>${entry.value == true ? "yes" : "no"}</td>
        </c:forEach>
      </tr>

   </c:forEach>





    </body>
    </html>