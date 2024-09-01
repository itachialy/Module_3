<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Danh sách lớp</title>
</head>
<body>
<h1>Danh sách học viên C0324m4</h1>
<a href="?action=create">Thêm mới học viên</a>
<table border="1px">
    <thead>
        <tr>
            <td>ID</td>
            <td>Tên</td>
            <td>Email</td>
            <td>Điểm</td>
            <td>Xếp loại</td>
            <td>Lớp</td>
            <td>Update</td>
        </tr>
    </thead>

    <tbody>
    <c:forEach items="${studentList}" var="student">
        <tr>
            <td>${student.id}</td>
            <td>${student.name}</td>
            <td>${student.email}</td>
            <td>${student.point}</td>
            <td>
                <c:choose>
                    <c:when test="${student.point > 8.9}">
                        Loại giỏi
                    </c:when>
                    <c:when test="${student.point > 7.9}">
                        Loại khá
                    </c:when>
                    <c:when test="${student.point > 6.9}">
                        Loại Trung binh`
                    </c:when>
                    <c:when test="${student.point <6.9}">
                        Yếu
                    </c:when>
                </c:choose>
            </td>
            <td>${student.className}</td>
            <td>
                <a href="?action=edit">Chỉnh sửa</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>


</body>
</html>
