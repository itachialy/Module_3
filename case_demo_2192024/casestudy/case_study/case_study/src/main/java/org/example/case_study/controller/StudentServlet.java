package org.example.case_study.controller;

import org.example.case_study.model.Class;
import org.example.case_study.model.Student;
import org.example.case_study.service.IStudentService;
import org.example.case_study.service.StudentServiceImpl;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@WebServlet(name = "StudentServlet", value = "/student-servlet")
public class StudentServlet extends HttpServlet {
    private final IStudentService iStudentService = new StudentServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedInUser") == null) {
            // Nếu không có phiên hoặc không có thông tin người dùng trong phiên, chuyển hướng đến trang đăng nhập
            response.sendRedirect("home.jsp");
            return;
        }

        String action = request.getParameter("action");
        if (action == null){
            action = "";
        }
        switch (action){
            case "create" :
                showCreateForm(request, response);
                break;
            case "edit" :
                showUpdateForm(request, response);
                break;
            case "delete" :
                deleteStudent(request,response);
                break;
//            case "search" :
//                searchByName(request, response);
            default:
                findAll(request, response);
        }
    }

//    private void searchByName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String name = request.getParameter("search");
//
//        // Kiểm tra ô search không được rỗng
//        if (name == null || name.trim().isEmpty()) {
//            // Chuyển hướng về student-servlet với thông báo lỗi
//            request.setAttribute("errorMessage", "Vui lòng nhập tên cần tìm kiếm.");
//            findAll(request,response);
//            return; // Dừng xử lý
//        }
//
//        // Nếu không rỗng thì thực hiện tìm kiếm
//        List<Student> students = iStudentService.searchByName(name);
//        request.setAttribute("students", students);
//        RequestDispatcher requestDispatcher = request.getRequestDispatcher("list.jsp");
//        requestDispatcher.forward(request, response);
//    }




    private void showUpdateForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("sid"));
        Student studentList = iStudentService.getStudentById(id);
        List<Class> classList = iStudentService.findAllClass();
        request.setAttribute("st", studentList);
        request.setAttribute("listU", classList);
        // Điều hướng đến trang JSP hiển thị chi tiết sinh viên
        RequestDispatcher dispatcher = request.getRequestDispatcher("update.jsp");
        dispatcher.forward(request, response);
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Class> list = iStudentService.findAllClass();
        request.setAttribute("listCr", list);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("create.jsp");
        requestDispatcher.forward(request, response);
    }

    private void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("Search");
        if(name == null) name = ""; // Kiểm tra nếu không có giá trị
        List<Student> list = iStudentService.searchByName(name);
        request.setAttribute("students", list);
        request.setAttribute("listClass", iStudentService.findAllClass());
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("list.jsp");
        requestDispatcher.forward(request, response);
    }


    private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("sid"));
        iStudentService.deleteStudent(id);
        response.sendRedirect("student-servlet");
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null){
            action = "";
        }
        switch (action){
            case "create" :
                try {
                    addNewStudent(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "edit" :
                try {
                    save(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            default:
                findAll(request, response);
        }
    }
//    private void addNewStudent(HttpServletRequest request, HttpServletResponse response) {
//        String name = request.getParameter("name");
//        Date birthday = Date.valueOf(request.getParameter("birthday"));
//        int gender = Integer.parseInt(request.getParameter("gender"));
//        String email = (request.getParameter("email"));
//        double point = Double.parseDouble(request.getParameter("point"));
//        int classId = Integer.parseInt(request.getParameter("classId"));
//        Class clazz = new Class(classId);
//        try {
//            if (iStudentService.emailExists(email)) {
//                request.setAttribute("errorMessage", "Email đã tồn tại. Vui lòng nhập lại.");
//                request.setAttribute("name", name);
//                request.setAttribute("birthday", birthday);
//                request.setAttribute("gender", gender);
//                request.setAttribute("point", point);
//                request.setAttribute("classId", classId);
//                request.setAttribute("listCr", iStudentService.findAllClass());
//                request.getRequestDispatcher("create.jsp").forward(request, response);
//            } else {
//                Student student = new Student(name, birthday, gender, email, point, clazz);
//                iStudentService.addNewStudent(student);
//                response.sendRedirect("student-servlet");
//            }
//
//        } catch (ServletException | IOException | SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
private void addNewStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
    String name = request.getParameter("name");
    Date birthday = Date.valueOf(request.getParameter("birthday"));
    int gender = Integer.parseInt(request.getParameter("gender"));
    String email = (request.getParameter("email"));
    double point = Double.parseDouble(request.getParameter("point"));
    int classId = Integer.parseInt(request.getParameter("classId"));
    if (regaxName(name)) {
        request.setAttribute("errorMessage","Tên chỉ gồm chữ, không chứa số hay kí tự.");
        forwardToCreateForm(request,response,name,birthday,gender,email,point,classId);
        return;
    }
    if (regaxDob(birthday)) {
        request.setAttribute("errorMessage","Vui lòng nhập độ tuổi tử 15 đến 45.");
        forwardToCreateForm(request,response,name,birthday,gender,email,point,classId);
        return;
    }
    if (iStudentService.emailExists(email)){
        request.setAttribute("errorMessage","Email đã tồn tại, vui lòng nhập lại.");
        forwardToCreateForm(request,response,name,birthday,gender,email,point,classId);
        return;
    }
    if (point < 0 || point > 10) {
        request.setAttribute("errorMessage", "Điểm chỉ nằm trong khoảng 1 đến 10.");
        forwardToCreateForm(request,response,name,birthday,gender,email,point,classId);
        return;
    }
    iStudentService.addNewStudent(new Student(name,birthday,gender,email,point, new Class(classId)));
    response.sendRedirect("student-servlet");

}
    private void forwardToCreateForm(HttpServletRequest request, HttpServletResponse response, String name, Date birthday,int gender, String email, double point, int classId) throws ServletException, IOException {
        // Thiết lập thuộc tính cho request
        request.setAttribute("name", name);
        request.setAttribute("birthday", birthday);
        request.setAttribute("gender", gender);
        request.setAttribute("email", email);
        request.setAttribute("point", point);
        request.setAttribute("classId", classId);
//        request.setAttribute("errorMessage", errorMessage); // Thêm thông báo lỗi

        List<Class> classList = iStudentService.findAllClass();
        request.setAttribute("listCr", classList);

        // Chuyển hướng đến trang tạo mới
        request.getRequestDispatcher("create.jsp").forward(request, response);
    }

    private void save(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        Date birthday = Date.valueOf(request.getParameter("birthday"));
        int gender = Integer.parseInt(request.getParameter("gender"));
        String email = request.getParameter("email");
        double point = Double.parseDouble(request.getParameter("point"));
        int classId = Integer.parseInt(request.getParameter("classId"));

        Student existingStudent = iStudentService.getStudentById(id);
        String existingEmail = existingStudent.getEmail();

        // Kiểm tra email chỉ nếu nó khác với email hiện tại
        if (!email.equals(existingEmail) && iStudentService.emailExists(email)) {
            // Lưu lại thông tin sinh viên cũ
            Student student = new Student(id, name, birthday, gender, email, point, new Class(classId));
            request.setAttribute("st", student);
            request.setAttribute("listU", iStudentService.findAllClass()); // Giữ lại danh sách lớp
            request.setAttribute("errorMessage", "Email đã tồn tại. Vui lòng nhập lại.");
            request.getRequestDispatcher("update.jsp").forward(request, response);
        } else {
            Class clazz = new Class(classId);
            Student student = new Student(id, name, birthday, gender, email, point, clazz);
            iStudentService.save(student);
            response.sendRedirect("student-servlet");
        }
    }
    private boolean regaxName(String name) {
        return !name.matches("^[\\p{L}\\s]{1,50}$");
    }
    private boolean regaxDob(Date dob) {
        LocalDate currentDate = LocalDate.now();
        LocalDate birthDate = dob.toLocalDate();
        int age = Period.between(birthDate, currentDate).getYears();
        return !(age >= 15 && age <= 45);
    }
    }
