<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>宿舍楼管理</title>
</head>
<body>
<h2>宿舍楼列表</h2>
<a href="${pageContext.request.contextPath}/jsp/admin/dormitory_add.jsp">添加新宿舍楼</a><br>

<table border="1">
    <tr>
        <th>ID</th>
        <th>宿舍楼名称</th>
        <th>地址</th>
    </tr>
    <%
        List<Dormitory> dormitories = (List<Dormitory>) request.getAttribute("dormitories");
        for (Dormitory d : dormitories) {
    %>
    <tr>
        <td><%= d.getId() %></td>
        <td><%= d.getName() %></td>
        <td><%= d.getAddress() %></td>
    </tr>
    <% } %>
</table>

</body>
</html>
