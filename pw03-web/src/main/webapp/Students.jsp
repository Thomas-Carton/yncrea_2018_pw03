<%@page import="yncrea.pw03.config.entity.Student, java.util.List" contentType="text/html" pageEncoding="UTF-8" %>
<%! List<Student> students; %>
<%students = (List<Student>) request.getAttribute("students"); %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Students</title>
</head>
<body>
<div class="container">
<h1>Students</h1>
<ul>
    <% for (Student currentStudent : students) { %>
    <li><%=currentStudent.getLastname() %> <%=currentStudent.getFirstname() %></li>
    <%} %>
</ul>
</div>


</body>
</html>
