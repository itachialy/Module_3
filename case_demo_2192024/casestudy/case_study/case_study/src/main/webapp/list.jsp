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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css"> <%-- link icon ìm kiếm --%>


    <link href="https://cdn.datatables.net/1.11.5/css/dataTables.bootstrap5.min.css" rel="stylesheet"> <%--Link bootstrap hỗ trợ paging sorting--%>
</head>
<body>
<div class="container mt-4">
    <h1 class="page-title">Danh sách học viên CODEGYMER</h1>
 <!-- form serarch -->
    <form class="d-flex" action="student-servlet" method="get">
        <input type="text" name="Search" id="SearchBox" class="form-control me-2" placeholder="Tìm kiếm theo tên...">
        <button class="btn btn-outline-secondary me-2" type="submit">
            <i class="fas fa-search"></i> <!-- Biểu tượng kính lúp -->
        </button>
    </form>

    <table id="mainTable" class="table table-striped table-bordered">
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
        <c:choose>

            <c:when test="${not empty students}">
                <c:forEach items="${students}" var="s" varStatus="status">
                    <tr>
                        <td>${status.index + 1}</td> <!-- Tính STT chính xác -->
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
                            <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleteModal" data-id="${s.id}" data-name="${s.name}">Xóa</button>
                        </td>
                    </tr>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <tr>
                    <td colspan="10" class="text-center">Không có sinh viên nào được tìm thấy</td>
                </tr>
            </c:otherwise>
        </c:choose>
        </tbody>
    </table>
    <!-- Modal xác nhận action delete -->
    <div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="deleteModalLabel">Xác nhận xóa</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    Bạn có chắc chắn muốn xóa học viên <span id="studentName"></span> không?
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                    <a id="confirmDelete" href="#" class="btn btn-danger">Xóa</a>
                </div>
            </div>
        </div>
    </div>
    <div class="text-left">
        <a href="?action=create" class="btn btn-success">Thêm mới học viên</a>
    </div>
</div>


<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>


    <!-- 2 link để hỗ trợ paging sorting */ -->
<script src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.11.5/js/dataTables.bootstrap5.min.js"></script>
<script>
    $(document).ready(function() {
        $('#mainTable').DataTable({
            "dom" : 'lrtip',
            "lengthChange": false,
            "pageLength": 5,
            "columnDefs": [
                { "orderable": false, "targets": 7 }
            ]
        });
    });

    <!-- modal xóa -->
    $('#deleteModal').on('show.bs.modal', function (event) {
        let button = $(event.relatedTarget); // Nút kích hoạt modal
        let studentId = button.data('id'); // Lấy giá trị ID từ nút
        let studentName = button.data('name'); // Lấy giá trị tên từ nút
        let modal = $(this);
        let deleteUrl = "?action=delete&sid=" + studentId; // Tạo URL xóa
        modal.find('#studentName').text(studentName); // Cập nhật tên học viên trong modal
        modal.find('#confirmDelete').attr('href', deleteUrl);   // Cập nhật liên kết "Xóa" trong modal
    });
</script>


</body>
</html>
