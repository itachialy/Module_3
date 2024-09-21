<%--<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>--%>

<%--<html>--%>
<%--<head>--%>
<%--    <meta charset="UTF-8">--%>
<%--    <meta name="viewport" content="width=device-width, initial-scale=1.0">--%>
<%--    <title>Thêm mới học viên</title>--%>

<%--</head>--%>
<%--<body>--%>

<%--<form action="student-servlet?action=create" method="post">--%>
<%--    <h1>Thêm mới học viên ở đây</h1>--%>
<%--    Tên : <input type="text" name="name" required> <br>--%>
<%--    Birthday : <input type="date" name="birthday" required> <br>--%>
<%--    Giới tính : <input type="radio" name="gender" value="1" required>Nam--%>
<%--    <input type="radio" name="gender" value="0" required>Nữ  <br>--%>
<%--    Email : <input type="text" name="email" required> <br>--%>
<%--    Điểm : <input type="number" name="point" required> <br>--%>
<%--    ID Class : <input type="number" name="classId" required> <br>--%>
<%--    <input type="submit" value="Thêm mới">--%>
<%--</form>--%>

<%--</body>--%>
<%--</html>--%>


<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thêm mới học viên</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/create.css"> <!-- Liên kết đến file CSS -->

    <style>

    </style>
</head>
<body>
<div class="container mt-5">
    <div class="form-container">
<%--        <h1 class="text-center mb-4">Thêm mới học viên</h1>--%>
        <h1 class="header-title">Thêm Mới Học Viên</h1>
        <form action="student-servlet?action=create" method="post">
            <div class="form-group">
                <label for="name">Tên:</label>
                <input type="text" class="form-control form-control-lg" id="name" name="name" value="${name}" required>
            </div>

            <div class="form-group">
                <label for="birthday">Ngày sinh:</label>
                <input type="date" class="form-control form-control-lg" id="birthday" name="birthday" value="${birthday}" required>
            </div>

            <div class="form-group">
                <label>Giới tính:</label><br>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" id="genderMale" name="gender" value="1" <% if ("1".equals(request.getAttribute("gender"))) { %> checked <% } %> required>
                    <label class="form-check-label" for="genderMale">Nam</label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" id="genderFemale" name="gender" value="0" <% if ("0".equals(request.getAttribute("gender"))) { %> checked <% } %> required>
                    <label class="form-check-label" for="genderFemale">Nữ</label>
                </div>
            </div>


            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" class="form-control form-control-lg" id="email" name="email" value="${email}" required>
            </div>

            <div class="form-group">
                <label for="point">Điểm:</label>
                <input type="text" class="form-control form-control-lg" id="point" name="point" value="${point}" required>
            </div>

<%--            <div class="form-group">--%>
<%--                <label for="classId">ID Lớp:</label>--%>
<%--                <input type="number" class="form-control form-control-lg" id="classId" name="classId" required>--%>
<%--            </div>--%>
            <div class="form-group">
                <label for="classId">Lớp học:</label>
                <select class="form-control form-control-lg" id="classId" name="classId" required>
                    <option value="" disabled selected>Chọn lớp học</option>
                    <option value="1" <% if ("1".equals(request.getAttribute("classId"))) { %> selected <% } %>>Backend-01</option>
                    <option value="2" <% if ("2".equals(request.getAttribute("classId"))) { %> selected <% } %>>Backend-02</option>
                    <option value="3" <% if ("3".equals(request.getAttribute("classId"))) { %> selected <% } %>>Frontend-01</option>
                    <option value="4" <% if ("4".equals(request.getAttribute("classId"))) { %> selected <% } %>>Fullstack</option>
                </select>
            </div>



            <button type="submit" class="btn btn-primary btn-lg btn-block">Thêm mới</button>
        </form>
    </div>
</div>
<%-- Modal for email validation error --%>
<div class="modal fade" id="emailErrorModal" tabindex="-1" aria-labelledby="emailErrorModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="emailErrorModalLabel">Lỗi</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                ${errorMessage}
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
            </div>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

<script>
    <% if (request.getAttribute("errorMessage") != null) { %>
    let emailErrorModal = new bootstrap.Modal(document.getElementById('emailErrorModal'));
    emailErrorModal.show();
    <% } %>
</script>
</body>
</html>
