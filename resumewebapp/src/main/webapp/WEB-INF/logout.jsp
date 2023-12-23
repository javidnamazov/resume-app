<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 17.08.2023
  Time: 08:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Logout</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css">
</head>
<body class="login_background">
<form action="logout" method="POST">
    <div class="col-4 container login_fix_"></div>

    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <button type="submit" class="btn btn-primary">Logout</button>
</form>

</body>
</html>
