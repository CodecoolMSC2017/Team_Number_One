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
            <td>${result.asstit}</td>
            <td>${result.submissionDate}</td>
            <td>${result.maxScore}</td>
            <td>${result.actualScore}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>