<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<head>

</head>

<body>

<h1>Please fill the data in the form below.</h1>
<br>

<form action="/newPost" method="post">
    <input type="text" name="content">Content<br><br>
    <input type="text" name="entryStatus">Entry status<br><br>
    <input type="text" name="entryType">Entry type<br><br>
    <input type="submit" value="Add">
</form>

<form action="<%=request.getContextPath()%>/appLogout" method="post">
    <input type="submit" value="Logout"/>
</form>

</body>

</html>