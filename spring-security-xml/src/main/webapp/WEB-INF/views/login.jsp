<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
</head>
<body class="container">
<form action="/login" method="post" role="form">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <legend>Login Form</legend>
    <div class="form-group">
        <label for="username"></label>
        <input type="text" class="form-control" name="username" id="username" placeholder="username...">
    </div>
    <div class="form-group">
        <label for="password"></label>
        <input type="password" class="form-control" name="password" id="password" placeholder="password...">
    </div>
    <div class="form-group">
        <input type="checkbox" name="rememberMe">
    </div>
    <button type="submit" class="btn btn-primary">Login</button>
</form>
</body>
</html>