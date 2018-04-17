package com.codecool.web.servlet;

import com.codecool.web.model.User;
import com.codecool.web.service.DataStorage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.ConnectException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = (Connection)req.getServletContext();
        DataStorage DS = new DataStorage(connection);
        List<User> registered = DS.getUserList();

        String userName = req.getParameter("username");

        boolean notOccupiedName = true;
        for (User usr: registered) {
            if (usr.getName().equals(userName)) {
                notOccupiedName = false;
            }
        }

        if(notOccupiedName){
            DS.addUser(req.getParameter("email"),
                    req.getParameter("password"),
                    userName,
                    req.getParameter("role"));

            resp.sendRedirect("index.html");
        }
        else {
            req.setAttribute("notAvailable", true);
            req.getRequestDispatcher("register.jsp").forward(req, resp);

        }
    }
}