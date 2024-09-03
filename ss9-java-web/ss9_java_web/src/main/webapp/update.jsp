<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
Form chỉnh sửa ở đây nhé

<form action="?action=edit" method="post">
    ID: <input value="${st.id}" type="number" name="id" readonly> <br>
    Tên: <input value="${st.name}" type="text" name="name"> <br>
    Lớp: <input value="${st.className}" type="text" name="className">  <br>
    Giới tính :  <input type="radio" name="gender" value="1" ${st.gender == 1? "checked" : ""}>Nam
                 <input type="radio" name="gender" value="0" ${st.gender == 0? "checked" : ""}>Nữ  <br>
    Điểm: <input value="${st.point}" type="number" name="point"> <br>
    <input type="submit" value="Cập nhật">

</form>
</body>
</html>
