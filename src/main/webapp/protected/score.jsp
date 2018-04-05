<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>User Profile</title>
    <link rel="stylesheet" type="text/css" href="style.css">
    <link href="https://fonts.googleapis.com/css?family=Josefin+Sans" rel="stylesheet">
</head>
<body>
<h1 id="welcomeText">Welcome ${user.name}!</h1>
<br>
<table border=1>
    <thead>
    <tr>
        <th>Assignment Title</th>
        <th>Submission Date</th>
        <th>Max Score</th>
        <th>Student Score</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${results}" var="result">
        <tr>
            <td>${result.ap.title}</td>
            <td>${result.submissionDate}</td>
            <td>${result.ap.maxScore}</td>
            <td>${result.score}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<br>
<div>
    <img src="${path}"
         width="600" height="400" border="0" usemap="#chart"/>
</div>
<br>
<form action="curriculum" method="GET">
    <input type="submit" name="showUsers" value="Back to User List">
</form>
<form id="backToCurriculum" action="backToMain" method="GET">
    <input id="button" type="submit" value="Back to curriculum">
</form>
</body>
</html>