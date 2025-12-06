<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加宿舍楼</title>
</head>
<body>
<h2>添加宿舍楼</h2>
<form action="${pageContext.request.contextPath}/dormitories" method="post">
    宿舍楼名称：<input type="text" name="name" required /><br/>
    地址：<input type="text" name="address" required /><br/>
    <input type="submit" value="提交" />
</form>

</body>
</html>
