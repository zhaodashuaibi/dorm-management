<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加学生</title>
</head>
<body>
<h2>添加学生</h2>
<form action="${pageContext.request.contextPath}/students" method="post">
    学号：<input type="text" name="studentNo" required /><br/>
    姓名：<input type="text" name="name" required /><br/>
    性别：<input type="text" name="gender" /><br/>
    学院：<input type="text" name="college" /><br/>
    班级：<input type="text" name="class" /><br/>
    电话：<input type="text" name="phone" /><br/>
    <input type="submit" value="提交" />
</form>

</body>
</html>
