package com.codecool.web.servlet;

import com.codecool.web.dao.DatabaseUserDao;
import com.codecool.web.dao.UserDao;
import com.codecool.web.model.User;
import com.codecool.web.service.AttendanceHandler;
import com.codecool.web.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


@WebServlet("/attend")
public class AttendanceServlet extends AbstractServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try(Connection connection = getConnection(request.getServletContext())) {
            UserDao uDao = new DatabaseUserDao(connection);
            UserService uService = new UserService(uDao);
            AttendanceHandler AH = new AttendanceHandler(uService);
        String studentName = request.getParameter("studentName");
        String inputDate = request.getParameter("date");

        List<User> result = AH.filterSearch(studentName, inputDate);

        request.setAttribute("result", result);
        request.setAttribute("inputDate", inputDate);


            request.setAttribute("userList", AH.getStudentUserList());
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        request.getRequestDispatcher("protected/attendance.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String givenDate = request.getParameter("date2");
        String isHere = request.getParameter("isHere");
        String id = request.getParameter("userID");
        try (Connection connection = getConnection(request.getServletContext())) {
            UserDao uDao = new DatabaseUserDao(connection);
            UserService uService = new UserService(uDao);
            AttendanceHandler AH = new AttendanceHandler(uService);
            AH.saveAttendance(id, givenDate, isHere);

            request.setAttribute("userList", AH.getStudentUserList());
            request.getRequestDispatcher("protected/attendance.jsp").forward(request, response);
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }
}
