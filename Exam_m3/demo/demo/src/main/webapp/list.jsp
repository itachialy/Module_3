<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>StudentList</title>
    <link href="https://cdn.datatables.net/1.11.5/css/dataTables.bootstrap5.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div>
    <a href="?action=create" class="btn btn-success">Thêm mới học viên</a>
</div>
<table id="mainTable" class="table table-bordered">
    <thead>
    <tr>
        <th>STT</th>
        <th>Tên</th>
        <th>Giới tính</th>
        <th>Email</th>
        <th>Lớp</th>
        <th>Chức năng</th> <!-- Không gộp thành hai ô -->
    </tr>
    </thead>
    <tbody>
    <c:choose>
        <c:when test="${not empty students}">
            <c:forEach items="${students}" var="s" varStatus="status">
                <tr>
<%--                    <td>${status.index + 1}</td>--%>
                    <td>${s.id}</td>
                    <td>${s.name}</td>
                    <td>
                        <c:choose>
                            <c:when test="${s.gender == 1}">Nam</c:when>
                            <c:otherwise>Nữ</c:otherwise>
                        </c:choose>
                    </td>
                    <td>${s.email}</td>
                    <td>${s.clazz.className}</td>
                    <td>
                        <div>
                            <a href="?action=edit&sid=${s.id}">Sửa</a>
                            <button type="button" class="btn btn-danger btn-sm" data-bs-toggle="modal" data-bs-target="#deleteModal" data-id="${s.id}" data-name="${s.name}">Xóa</button>
                        </div>
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
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close">X</button>
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

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script> <%-- link boot modal xóa --%>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.11.5/js/dataTables.bootstrap5.min.js"></script>
<script>
    $(document).ready(function() {
        $('#mainTable').DataTable({
            "dom": 'lrtip',
            "lengthChange": false,
            "pageLength": 5,
            "columnDefs": [
                {"orderable": false, "targets":5 }
            ]
        });
    });
</script>
<script>
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
