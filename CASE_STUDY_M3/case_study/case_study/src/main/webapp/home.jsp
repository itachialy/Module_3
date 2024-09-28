<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Trang Chính</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/index.css">
</head>
<body>
<div class="container">
    <h1 class="text-center">Chào mừng đến với hệ thống CODEGYMER</h1>

    <!-- Hiển thị thông báo nếu có -->
    <c:if test="${not empty sessionScope.loginMessage}">
        <div class="alert alert-success alert-dismissible fade show" role="alert">
            <c:out value="${sessionScope.loginMessage}" />
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
        <c:remove var="loginMessage" scope="session"/>
    </c:if>

    <a href="student-servlet">Danh sách học viên</a> <br>
</div>
<!-- Bootstrap JS and dependencies -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
    document.addEventListener('DOMContentLoaded', function () {
        var successAlert = document.querySelector('.alert-success'); // Chọn thông báo thành công
        if (successAlert) {
            successAlert.style.display = 'block'; // Hiển thị thông báo
            setTimeout(function () {
                successAlert.style.opacity = 0; // Tạo hiệu ứng mờ dần
                setTimeout(function () {
                    successAlert.style.display = 'none'; // Ẩn thông báo hoàn toàn sau khi mờ dần
                }, 500); // Thời gian cho hiệu ứng mờ dần (0.5 giây)
            }, 2000); // Thời gian hiển thị thông báo (2 giây)
        }
    });
</script>
</body>
</html>
