<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<head>

    <style>
        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
        }
        th, td {
            padding: 5px;
            text-align: center;
        }
    </style>

</head>

<body>

<%int counter = 1;%>

<ol>
    <c:forEach var="i" items="${entryList}">

        <table>

            <tr>

                <th>Id</th>
                <th>Date of creation</th>
                <th>Status</th>
                <th style="width:100px">Type</th>
                <th>Creator</th>
                <th>Content</th>
            </tr>

            <tr>
                <td><%=counter%></td>
                <td>${i.getDateOfCreation()}</td>
                <td>${i.getStatus()}</td>
                <td style="width:100px">${i.getType()}</td>
                <td>${i.getUser().getViewAccountName()}</td>
                <td>${i.getContent()}</td>
            </tr>

            <% counter = counter + 1;%>

        </table>
        <br>

    </c:forEach>
</ol>



</body>

</html>