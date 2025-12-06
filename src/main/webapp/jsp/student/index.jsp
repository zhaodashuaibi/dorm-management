<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生首页</title>
</head>
<body>
<h2>欢迎来到学生首页</h2>

<p>学生信息：<%= session.getAttribute("currentUser") %></p>

<a href="${pageContext.request.contextPath}/repair_form.jsp">报修</a><br>
<a href="${pageContext.request.contextPath}/login?op=logout">登出</a>
</body>
</html>
