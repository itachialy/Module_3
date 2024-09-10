<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1 style="font-family: Serif, sans-serif">Danh sách giảng viên</h1>

<table border="1px">
    <thead>
    <tr>
        <th>ID</th>
        <th>Tên</th>
        <th>Birthday</th>
        <th>Lương</th>
        <th>Update</th>
        <th>Delete</th>
    </tr>
    </thead>

    <tbody>
    <c:forEach items="${listT}" var="s">
        <tr>
            <td>${s.id}</td>
            <td>${s.name}</td>
            <td>${s.birthday}</td>
            <td>${s.salary}</td>
            <td>
                <a href="#">Chỉnh sửa</a>
            </td>
            <td>
                <a href="#">Xóa</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div style="text-align: left">
    <a href="?action=create">Thêm mới giảng viên</a>
</div>

</body>
</html>
