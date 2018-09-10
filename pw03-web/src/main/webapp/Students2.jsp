<%@page import="yncrea.pw03.config.entity.Student, yncrea.pw03.config.entity.Course" contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="yncrea.pw03.config.entity.Work" %>
<%@ page import="java.util.stream.Collector" %>
<%@ page import="java.util.stream.Collectors" %>
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

    <% for (Student currentStudent : students) { %>
    <div class="panel panel-default">
        <div class="panel-heading"><strong><%=currentStudent.getLastname() %> <%=currentStudent.getFirstname() %></strong></div>

        <table class="table table-bordered table-stripped">
            <% for (int i = 0; i< currentStudent.getCourses().size(); i++){%>
            <tr>
                <td class="col-md-1 text-right"><%=currentStudent.getCourses().get(i).getName()%></td>
                <td>    <%
                        if(currentStudent.getCourses().get(i).isValidated()) {
                        %>
                        <span class="label label-success">success</span>
                    <%
                        }else{
                            %>
                    <span class="label label-danger">failure</span>
                    <%
                        }
                    %>
                </td>
                <td class="col-md-11">
                    <% for (Work work : currentStudent.getCourses().get(i).getWorks()){%>
                    <%=work.getName()%> <span class="badge"><%=work.getGrade()%>/20</span>,
                    <%}%>
                </td>
            </tr>
            <%}%>
        </table>
    </div>


    <%} %>

</div>


</body>
</html>
