<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>Title</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--<h1 style="font-family: Serif, sans-serif">Danh sách học viên CODEGYMER</h1>--%>

<%--<table border="1px">--%>
<%--    <thead>--%>
<%--    <tr>--%>
<%--        <th>ID</th>--%>
<%--        <th>Tên</th>--%>
<%--        <th>Birthday</th>--%>
<%--        <th>Giới tính</th>--%>
<%--        <th>Email</th>--%>
<%--        <th>Điểm</th>--%>
<%--        <th>Xếp loại</th>--%>
<%--        <th>Lớp</th>--%>
<%--        <th>Update</th>--%>
<%--        <th>Delete</th>--%>
<%--    </tr>--%>
<%--    </thead>--%>

<%--    <tbody>--%>
<%--    <c:forEach items="${students}" var="s">--%>
<%--        <tr>--%>
<%--            <td>${s.id}</td>--%>
<%--            <td>${s.name}</td>--%>
<%--            <td>${s.birthday}</td>--%>
<%--            <td>--%>
<%--                <c:if test="${s.gender == 1}">--%>
<%--                    Nam--%>
<%--                </c:if>--%>
<%--                <c:if test="${s.gender == 0}">--%>
<%--                    Nữ--%>
<%--                </c:if>--%>
<%--            </td>--%>
<%--            <td>${s.email}</td>--%>
<%--            <td>${s.point}</td>--%>
<%--            <td>--%>
<%--                <c:choose>--%>
<%--                    <c:when test="${s.point > 8.9}">--%>
<%--                        Loại Giỏi--%>
<%--                    </c:when>--%>
<%--                    <c:when test="${s.point > 7.9}">--%>
<%--                        Loại Khá--%>
<%--                    </c:when>--%>
<%--                    <c:when test="${s.point > 6.9}">--%>
<%--                        Loại Trung Bình--%>
<%--                    </c:when>--%>
<%--                    <c:when test="${s.point <6.9}">--%>
<%--                        Loại Yếu--%>
<%--                    </c:when>--%>
<%--                </c:choose>--%>
<%--            </td>--%>
<%--            <td>${s.clazz.className}</td>--%>
<%--            <td>--%>
<%--                <a href="?action=edit&sid=${s.id}">Chỉnh sửa</a>--%>

<%--            </td>--%>
<%--            <td>--%>
<%--                <a href="#" onclick="showMess(${s.id})">Xóa</a>--%>
<%--            </td>--%>
<%--        </tr>--%>
<%--    </c:forEach>--%>
<%--    </tbody>--%>
<%--</table>--%>
<%--<div style="text-align: left">--%>
<%--    <a href="?action=create">Thêm mới học viên</a>--%>
<%--</div>--%>
<%--<script>--%>
<%--    function showMess(id){--%>
<%--        let option = confirm("Are you sure ??? ");--%>
<%--        if (option === true){--%>
<%--            window.location.href = "?action=delete&sid=" + id;--%>
<%--        }--%>
<%--    }--%>
<%--</script>--%>
<%--</body>--%>
<%--</html>--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Danh sách học viên</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/list.css"> <!-- Liên kết đến file CSS -->

</head>
<body>
<div class="container mt-4">
    <h1 class="page-title">Danh sách học viên CODEGYMER</h1>

    <table class="table table-striped table-bordered">
        <thead class="thead-dark">
        <tr>
            <th>ID</th>
            <th>Tên</th>
            <th>Ngày sinh</th>
            <th>Giới tính</th>
            <th>Email</th>
            <th>Điểm</th>
            <th>Xếp loại</th>
            <th>Lớp</th>
            <th>Cập nhật</th>
            <th>Xóa</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${students}" var="s">
            <tr>
                <td>${s.id}</td>
                <td>${s.name}</td>
                <td>${s.getDateFormat()}</td>
                <td>
                    <c:choose>
                        <c:when test="${s.gender == 1}">Nam</c:when>
                        <c:otherwise>Nữ</c:otherwise>
                    </c:choose>
                </td>
                <td>${s.email}</td>
                <td>${s.point}</td>
                <td>
                    <c:choose>
                        <c:when test="${s.point > 8.9}">Loại Giỏi</c:when>
                        <c:when test="${s.point > 7.9}">Loại Khá</c:when>
                        <c:when test="${s.point > 6.9}">Loại Trung Bình</c:when>
                        <c:otherwise>Loại Yếu</c:otherwise>
                    </c:choose>
                </td>
                <td>${s.clazz.className}</td>
                <td>
                    <a href="?action=edit&sid=${s.id}" class="btn btn-warning btn-sm">Chỉnh sửa</a>
                </td>
                <td>
                    <a href="#" class="btn btn-danger btn-sm" onclick="showMess(${s.id})">Xóa</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="text-left">
        <a href="?action=create" class="btn btn-success">Thêm mới học viên</a>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
    function showMess(id){
        let option = confirm("Are you sure ??? " + id);
        if (option === true){
            window.location.href = "?action=delete&sid=" + id;
        }
    }
</script>
</body>
</html>
