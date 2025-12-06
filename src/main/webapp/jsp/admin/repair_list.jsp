<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>报修管理</title>
</head>
<body>
<h2>报修列表</h2>
<table border="1">
    <tr>
        <th>ID</th>
        <th>学号</th>
        <th>标题</th>
        <th>描述</th>
        <th>状态</th>
        <th>操作</th>
    </tr>
    <%
        List<Repair> repairs = (List<Repair>) request.getAttribute("repairs");
        for (Repair r : repairs) {
    %>
    <tr>
        <td><%= r.getId() %></td>
        <td><%= r.getStudentId() %></td>
        <td><%= r.getTitle() %></td>
        <td><%= r.getDescription() %></td>
        <td><%= r.getStatus() %></td>
        <td><a href="${pageContext.request.contextPath}/repairs?op=resolve&id=<%= r.getId() %>">处理</a></td>
    </tr>
    <% } %>
</table>

</body>
</html>
