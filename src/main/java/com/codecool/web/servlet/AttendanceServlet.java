package com.codecool.web.servlet;

import com.codecool.web.model.User;
import com.codecool.web.service.AttendanceHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/attend")
public class AttendanceServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String studentName = request.getParameter("studentName");
        String inputDate = request.getParameter("date");

        List<User> result = AttendanceHandler.run(studentName, inputDate);

        request.setAttribute("result", result);
        request.setAttribute("inputDate", inputDate);
        request.setAttribute("userList", AttendanceHandler.getStudentUserList());
        request.getRequestDispatcher("protected/attendance.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("userList", AttendanceHandler.getStudentUserList());
        request.getRequestDispatcher("protected/attendance.jsp").forward(request, response);
    }
}
