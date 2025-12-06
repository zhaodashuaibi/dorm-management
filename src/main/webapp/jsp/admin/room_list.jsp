<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>房间管理</title>
</head>
<body>
<h2>房间列表</h2>
<a href="${pageContext.request.contextPath}/jsp/admin/room_add.jsp">添加新房间</a><br>

<table border="1">
    <tr>
        <th>ID</th>
        <th>房间号</th>
        <th>宿舍楼</th>
        <th>床位数</th>
        <th>已入住人数</th>
        <th>备注</th>
    </tr>
    <%
        List<Room> rooms = (List<Room>) request.getAttribute("rooms");
        for (Room r : rooms) {
    %>
    <tr>
        <td><%= r.getId() %></td>
        <td><%= r.getRoomNo() %></td>
        <td><%= r.getDormId() %></td>
        <td><%= r.getCapacity() %></td>
        <td><%= r.getOccupied() %></td>
        <td><%= r.getRemark() %></td>
    </tr>
    <% } %>
</table>

</body>
</html>
