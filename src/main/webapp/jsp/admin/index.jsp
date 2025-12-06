<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理员首页</title>
</head>
<body>
<h2>欢迎来到管理员首页</h2>

<p><strong>总房间数：</strong>${totalRooms}</p>
<p><strong>已入住房间数：</strong>${occupiedRooms}</p>
<p><strong>空闲房间数：</strong>${freeRooms}</p>
<p><strong>待处理报修单：</strong>${pendingRepairs}</p>

<hr>

<a href="${pageContext.request.contextPath}/students">学生管理</a><br>
<a href="${pageContext.request.contextPath}/rooms">房间管理</a><br>
<a href="${pageContext.request.contextPath}/dormitories">宿舍楼管理</a><br>
<a href="${pageContext.request.contextPath}/repairs">报修管理</a><br>
<a href="${pageContext.request.contextPath}/allocations">宿舍分配</a><br>
<a href="${pageContext.request.contextPath}/login?op=logout">登出</a>
</body>
</html>
