package org.example.case_study.controller;

import org.example.case_study.model.Account;
import org.example.case_study.service.IStudentService;
import org.example.case_study.service.StudentServiceImpl;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(name = "LoginServlet", value = "/login-servlet")
public class LoginServlet extends HttpServlet {
    IStudentService iStudentService = new StudentServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            String user = request.getParameter("username");
            String pass = request.getParameter("password");
            Account account = iStudentService.checkLogin(user, pass);
            if (account == null) {
                // Thêm tham số lỗi vào URL
                response.sendRedirect("index.jsp?error=true");
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("loggedInUser", account); // Lưu thông tin người dùng vào phiên
                request.getSession().setAttribute("loginMessage", "Login Thành Công!");
                response.sendRedirect("student-servlet"); // Chuyển hướng đến trang chính
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            response.sendRedirect("index.jsp");
        }
    }
}
