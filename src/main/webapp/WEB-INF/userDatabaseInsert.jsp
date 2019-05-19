<html>
<meta http-equiv="Refresh" content="5;url=login.jsp">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>

</head>

<body>

<h2>Created user</h2>
<h3>Username: ${createdUser.getUserName()}</h3>
<h3>LinkAccountName: ${createdUser.getLinkAccountName()}</h3>
<h3>ViewAccountName: ${createdUser.getViewAccountName()}</h3>
<h3>DateOfCreation: ${createdUser.getDateOfCreation()}</h3>
<h3>Status: ${createdUser.getStatus()}</h3>
<h3>Type: ${createdUser.getType()}</h3>

<h2>Please wait. You will be redirect to the WALL in 5 seconds.</h2>

</body>

</html>