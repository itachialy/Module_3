<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chỉnh sửa thông tin học viên</title>
</head>
<body>
<div>
    <h1>Cập nhật thông tin học viên</h1>
    <form action="?action=edit" method="post">
        <div>
            <label for="id"></label>
            <input type="hidden" id="id" name="id" readonly value="${st.id}">
        </div>
        <div>
            <label for="name">Tên:</label>
            <input type="text" id="name" name="name" value="${st.name}">
        </div>
        <div>
            <label>Giới tính:</label><br>
            <input type="radio" name="gender" value="1" ${st.gender == 1 ? "checked" : ""}> Nam
            <input type="radio" name="gender" value="0" ${st.gender == 0 ? "checked" : ""}> Nữ
        </div>
        <div>
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" value="${st.email}">
        </div>
        <div>
            <label for="classId">Lớp học:</label>
            <select  name="classId" id="classId" required>
                <c:forEach var="c" items="${listU}">
                    <option value="${c.classId}" ${c.classId == st.clazz.classId ? 'selected' : ''}>
                            ${c.className}
                    </option>
                </c:forEach>
            </select>
        </div>
        <button type="submit" class="btn btn-primary btn-lg btn-block">Cập nhật</button>
    </form>
</div>

<%--&lt;%&ndash; Modal for email validation error &ndash;%&gt;--%>
<%--<div class="modal" id="emailErrorModal" tabindex="-1" aria-labelledby="emailErrorModalLabel" aria-hidden="true">--%>
<%--    <div class="modal-dialog">--%>
<%--        <div class="modal-content">--%>
<%--            <div class="modal-header">--%>
<%--                <h5 class="modal-title" id="emailErrorModalLabel">Lỗi</h5>--%>
<%--                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close">X</button>--%>
<%--            </div>--%>
<%--            <div class="modal-body">--%>
<%--                ${errorMessage}--%>
<%--            </div>--%>
<%--            <div class="modal-footer">--%>
<%--                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>

<!-- Không cần Bootstrap JS và dependencies -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>

<%--<script>--%>
<%--    <% if (request.getAttribute("errorMessage") != null) { %>--%>
<%--    // JavaScript để hiển thị modal nếu có thông báo lỗi--%>
<%--    const emailErrorModal = document.getElementById('emailErrorModal');--%>
<%--    if (emailErrorModal) {--%>
<%--        emailErrorModal.style.display = 'block'; // Hiển thị modal--%>
<%--    }--%>
<%--    <% } %>--%>
<%--</script>--%>
</body>
</html>
