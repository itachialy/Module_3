package org.example.case_study.controller;

import org.example.case_study.service.teacherService.ITeacherService;
import org.example.case_study.service.teacherService.TeacherServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "TeacherServlet", value = "/teacher-servlet")

public class TeacherServlet extends HttpServlet {
    private final ITeacherService iTeacherService = new TeacherServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null){
            action = "";
        }
        switch (action){
            case "create" :
                break;
            case "edit" :
                break;
            case "delete" :
                break;
            default:
                findAll(request, response);
        }
        findAll(request, response);
    }

    private void findAll(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("listT", iTeacherService.findAll());
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("list_teacher.jsp");
        requestDispatcher.forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null){
            action = "";
        }
        switch (action){
            case "create" :
                break;
            case "edit" :
                break;
            case "delete" :
                break;
            default:
                findAll(request, response);
        }
        findAll(request, response);
    }

    }




