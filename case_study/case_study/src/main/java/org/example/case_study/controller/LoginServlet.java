package org.example.case_study.controller;

import org.example.case_study.model.Account;
import org.example.case_study.service.studentService.IStudentService;
import org.example.case_study.service.studentService.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    IStudentService iStudentService = new StudentServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String user = request.getParameter("username");
            String pass = request.getParameter("password");
            Account account = iStudentService.checkLogin(user, pass);
            if (account == null){
                response.sendRedirect("login.jsp");
            } else {
                response.sendRedirect("sucess.jsp");
            }

        }catch (Exception e){
            System.err.println(e.getMessage());;
        }
    }
}
