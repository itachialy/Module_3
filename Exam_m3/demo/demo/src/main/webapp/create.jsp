<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    <h1>Thêm Mới Học Viên</h1>
    <form action="?action=create" method="post">
        <div>
            <label for="name">Tên:</label>
            <input type="text" id="name" name="name" value="${students.name}" required>
        </div>

        <div>
            <label>Giới tính:</label>
            <input type="radio" id="genderMale" name="gender" value="1" ${students.gender == '1' ? 'checked' : ''} required>
            <label for="genderMale">Nam</label>
            <input type="radio" id="genderFemale" name="gender" value="0" ${students.gender == '0' ? 'checked' : ''} required>
            <label for="genderFemale">Nữ</label>
        </div>

        <div>
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" value="${students.email}" required>
        </div>

        <div>
            <label for="classId">Lớp học:</label>
            <select name="classId" id="classId" required>
                <option value="" disabled selected>Chọn lớp</option>
                <c:forEach var="c" items="${listCr}">
                    <option value="${c.classId}" ${c.classId == students.clazz.classId ? 'selected' : ''}>${c.className}</option>
             
                </c:forEach>
            </select>
        </div>
        <button type="submit" class="btn">Thêm mới</button>
    </form>
</div>
</body>
</html>
