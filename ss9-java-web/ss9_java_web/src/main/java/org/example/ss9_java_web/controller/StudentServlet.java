package org.example.ss9_java_web.controller;


import org.example.ss9_java_web.model.Student;
import org.example.ss9_java_web.service.IStudentService;
import org.example.ss9_java_web.service.StudentServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "StudentServlet", value = "/student-servlet")

public class StudentServlet extends HttpServlet {
    private final IStudentService iStudentService = new StudentServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                showCreateForm(request, response);
                break;
            case "edit":
                showUpdateForm(request, response);
                break;
            case "delete":
                break;
            default:
                findAll(request, response);
        }

        findAll(request, response);
    }

    private void showUpdateForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("update.jsp");
        requestDispatcher.forward(request, response);
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("create.jsp");
        requestDispatcher.forward(request, response);
    }

    private void findAll(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("studentList", iStudentService.findAll());
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("list.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                addNewStudent(request, response);
                break;
            case "edit":
                showUpdateForm(request, response);
                break;
            case "delete":
                break;
            default:
                findAll(request, response);
        }

        findAll(request, response);
    }

    private void addNewStudent(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String className = request.getParameter("className");
        double point = Double.parseDouble(request.getParameter("point"));
        Student student = new Student(name, email, className, point);
        iStudentService.addNewStudent(student);
    }

}
