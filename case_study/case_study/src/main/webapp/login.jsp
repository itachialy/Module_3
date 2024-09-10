
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Đăng Nhập</title>
</head>
<body>
<h1>Đăng Nhập</h1>
<form action="LoginServlet" method="post">
    <label for="username">Tên người dùng:</label>
    <input type="text" id="username" name="username" required><br><br>

    <label for="password">Mật khẩu:</label>
    <input type="text" id="password" name="password" required><br><br>

    <input type="submit" value="Đăng Nhập">
</form>

</body>
</html>
