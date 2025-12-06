<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>宿舍管理系统 - 登录</title>
</head>
<body>
<h2>宿舍管理系统 登录</h2>
<form action="${pageContext.request.contextPath}/login" method="post">
    用户名：<input type="text" name="username" required /><br/>
    密码：<input type="password" name="password" required /><br/>
    <input type="submit" value="登录" />
</form>
<c:if test="${not empty msg}">
    <p style="color:red">${msg}</p>
</c:if>
</body>
</html>
