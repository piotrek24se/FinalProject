<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<head>
</head>

<body>

<h1>Sorry. Actually there are no entries!</h1><br>

<form action="/addPost" method="get">
    <input type="submit" value="Add post"/>
</form>

<form action="<%=request.getContextPath()%>/appLogout" method="post">
    <input type="submit" value="Logout"/>
</form>

</body>

</html>