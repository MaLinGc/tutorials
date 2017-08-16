<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
</head>
<body class="container">
<h3>index page</h3>

<ul>
    <li><a href="${pageContext.request.contextPath}/admin">admin</a></li>
    <li><a href="${pageContext.request.contextPath}/info">info</a></li>
    <li><a href="${pageContext.request.contextPath}/anonymous">anonymous</a></li>
</ul>
</body>
</html>
