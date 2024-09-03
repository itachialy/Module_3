<%--
  Created by IntelliJ IDEA.
  User: KAIs
  Date: 8/31/2024
  Time: 5:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
Thêm mới học viên
<form action="?action=create" method="post">
    Tên: <input type="text" name="name"> <br>
    Lớp: <input type="text" name="className">  <br>
    Giới tính : <input type="radio" name="gender" value="1">Nam
    <input type="radio" name="gender" value="0">Nữ  <br>
    Điểm: <input type="number" name="point"> <br>
    <input type="submit" value="Thêm mới">

</form>
</body>
</html>
