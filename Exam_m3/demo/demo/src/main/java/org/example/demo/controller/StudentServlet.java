package org.example.demo.controller;

import org.example.demo.model.ClassName;
import org.example.demo.model.Student;
import org.example.demo.service.IStudentService;
import org.example.demo.service.StudentServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "StudentServlet", value = "")

public class StudentServlet extends HttpServlet {
    IStudentService iStudentService = new StudentServiceImpl();

    public StudentServlet() throws SQLException, ClassNotFoundException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                showCreateForm(request,response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteStudent(request, response);
                break;
            default:
                findAll(request,response);
        }

    }



    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("sid"));
        Student studentList = iStudentService.getStudentById(id);
        List<ClassName> classList = iStudentService.getAllClass();
        request.setAttribute("st", studentList);
        request.setAttribute("listU", classList);
        // Điều hướng đến trang JSP hiển thị chi tiết sinh viên
        RequestDispatcher dispatcher = request.getRequestDispatcher("update.jsp");
        dispatcher.forward(request, response);
    }

    private void save(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int gender = Integer.parseInt(request.getParameter("gender"));
        String email = request.getParameter("email");
        int classId = Integer.parseInt(request.getParameter("classId"));

        if (regaxName(name)) {
            request.setAttribute("errorMessage", "Tên không chứa số và kí tự đặc biệt.");
            forwardToEditForm(request, response, id, name, gender, email, classId);
            return;
        }
//       
//        if (iStudentService.checkEmailForUpdate(id, email)) {
//            request.setAttribute("errorMessage", "Email đã tồn tại. Vui lòng nhập lại.");
//            forwardToEditForm(request, response, id, name, gender, email, classId);
//            return;
//        }

    
        iStudentService.save(new Student(id, name, gender, email, new ClassName(classId)));
        response.sendRedirect("/");

    }



    private void forwardToEditForm(HttpServletRequest request, HttpServletResponse response, int id, String name, int gender, String email, int classId) throws ServletException, IOException {
        Student student = new Student(id, name, gender, email, new ClassName(classId));
        request.setAttribute("st", student);
        List<ClassName> classList = iStudentService.getAllClass();
        request.setAttribute("listU", classList);
        request.getRequestDispatcher("update.jsp").forward(request, response);
    }

    private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("sid"));
        iStudentService.deleteStudent(id);
        response.sendRedirect("/");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {

            case "create":
                addNewStudent(request,response);
                break;
            case"edit":
                try {
                    save(request,response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;

            default:
                findAll(request,response);
        }
    }

    private void addNewStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        int gender = Integer.parseInt(request.getParameter("gender"));
        String email = request.getParameter("email");
        int classId = Integer.parseInt(request.getParameter("classId"));

        if (regaxName(name)) {
            request.setAttribute("errorMessage", "Tên không chứa số và kí tự đặc biệt.");
            forwardCreateForm(request, response, name, gender, email, classId);
            return;
        }

        iStudentService.addNewStudent(new Student(name, gender, email, new ClassName(classId)));
        response.sendRedirect("/");
    }
    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ClassName> list = iStudentService.getAllClass();
        request.setAttribute("listCr", list);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("create.jsp");
        requestDispatcher.forward(request, response);
    }
    private void forwardCreateForm(HttpServletRequest request, HttpServletResponse response, String name, int gender, String email, int classId) throws ServletException, IOException {
        Student student = new Student(name, gender, email, new ClassName(classId));
        request.setAttribute("students", student);
        List<ClassName> classList = iStudentService.getAllClass();
        request.setAttribute("listCr", classList);
        request.getRequestDispatcher("create.jsp").forward(request, response);
    }

    private void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("students", iStudentService.getAllStudent());
        request.setAttribute("listClass", iStudentService.getAllClass());
        request.getRequestDispatcher("list.jsp").forward(request,response);
    }

    private boolean regaxName(String name) {
        return !name.matches("^[\\p{L}\\s]{1,150}$");
    }

}
