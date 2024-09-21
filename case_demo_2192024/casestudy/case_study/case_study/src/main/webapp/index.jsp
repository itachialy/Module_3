<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
    <meta charset="UTF-8">

    <title>Đăng Nhập</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/indess.css">
    <style>
        .alert {
            display: none; /* Ẩn thông báo lỗi khi trang được tải */
            opacity: 1;
            transition: opacity 1s ease-out; /* Hiệu ứng mờ dần */
        }
    </style>
</head>

<body>

<div class="container d-flex justify-content-center align-items-center" style="height: 100vh;">
    <div class="login-container">
        <h1 class="text-center">Đăng Nhập</h1>
        <c:if test="${param.error == 'true'}">
            <div id="error-alert" class="alert alert-danger" role="alert">
                <strong>Lỗi!</strong> Tên người dùng hoặc mật khẩu không chính xác.
            </div>
        </c:if>
        <form action="login-servlet" method="post">
            <div class="form-group">
                <label for="username">Tên người dùng:</label>
                <input type="text" class="form-control" id="username" name="username" required>
            </div>

            <div class="form-group">
                <label for="password">Mật khẩu:</label>
                <input type="password" class="form-control" id="password" name="password" required>
            </div>

            <button type="submit" class="btn btn-primary btn-block">Đăng Nhập</button>
        </form>
    </div>
</div>

<!-- Bootstrap JS and dependencies -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<script>
    document.addEventListener('DOMContentLoaded', function () {
        var errorAlert = document.getElementById('error-alert');
        if (errorAlert) {
            errorAlert.style.display = 'block'; // Hiển thị thông báo lỗi
            setTimeout(function () {
                errorAlert.style.opacity = 0; // Tạo hiệu ứng mờ dần
                setTimeout(function () {
                    errorAlert.style.display = 'none'; // Ẩn thông báo lỗi hoàn toàn
                }, 1000); // Thời gian cho hiệu ứng mờ dần
            }, 1000); // Thời gian hiển thị thông báo lỗi
        }
    });
</script>
</body>
</html>
