<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
</head>
<body class="container">
<h3>admin page</h3>
<h4><fmt:formatDate value="<%= new Date()%>" pattern="yyyy-MM-dd HH:mm:ss"/></h4>
</body>
</html>
