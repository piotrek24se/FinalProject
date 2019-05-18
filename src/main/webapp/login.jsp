<head>

</head>

<body>

<h1>Microblogging platform</h1>

<h3>Log-in</h3>

<p style="color: red">${SPRING_SECURITY_LAST_EXCEPTION.message}</p>

<form action="<%=request.getContextPath()%>/appLogin" method="POST">
    Enter Username:	<input type="text" name="app_username"/><br/><br/>
    Enter Password: <input type="password" name="app_password"/> <br/><br/>
    <input type="submit" value="Login"/>
</form>

</body>

</html>