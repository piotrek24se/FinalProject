<html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>

</head>

<body>

<h1>Microblogging platform</h1>

<h3>Sign-up</h3>

<form action="/userCreation" method="post">
    Username: <input type="text" name="username"/><br/><br/>
    Password: <input type="password" name="password"/> <br/><br/>
    LinkAccountName: <input type="text" name="linkAccountName"/><br/><br/>
    ViewAccountName: <input type="text" name="viewAccountName"/><br/><br/>
    UserStatus: <input type="text" name="status"/><br/><br/>
    UserType: <input type="text" name="type"/><br/><br/>
    <input type="submit" value="Sign-up"/>
</form>

</body>

</html>