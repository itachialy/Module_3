
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thêm mới học viên</title>

</head>
<body>

<form action="?action=create" method="post">
    <h1>Thêm mới học viên ở đây</h1>
    Tên: <input type="text" name="name"> <br>
    Birthday : <input type="date" name="birthday"> <br>
    Giới tính : <input type="radio" name="gender" value="1">Nam
    <input type="radio" name="gender" value="0">Nữ  <br>
    Email : <input type="text" name="email"> <br>
    Điểm: <input type="number" name="point"> <br>
    <input type="submit" value="Thêm mới">
</form>

</body>
</html>
