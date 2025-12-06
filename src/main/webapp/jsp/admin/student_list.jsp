<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生管理</title>
</head>
<body>
<h2>学生列表</h2>
<a href="${pageContext.request.contextPath}/jsp/admin/student_add.jsp">添加新学生</a><br>

<table border="1">
    <tr>
        <th>ID</th>
        <th>学号</th>
        <th>姓名</th>
        <th>班级</th>
        <th>电话</th>
    </tr>
    <%
        List<Student> students = (List<Student>) request.getAttribute("students");
        for (Student s : students) {
    %>
    <tr>
        <td><%= s.getId() %></td>
        <td><%= s.getStudentNo() %></td>
        <td><%= s.getName() %></td>
        <td><%= s.getClazz() %></td>
        <td><%= s.getPhone() %></td>
    </tr>
    <% } %>
</table>

</body>
</html>
