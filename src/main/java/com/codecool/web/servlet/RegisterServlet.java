package com.codecool.web.servlet;

import com.codecool.web.dao.DatabaseUserDao;
import com.codecool.web.dao.UserDao;
import com.codecool.web.model.User;
import com.codecool.web.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/register")
public class RegisterServlet extends AbstractServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = getConnection(req.getServletContext())) {
            UserDao userDao = new DatabaseUserDao(connection);
            UserService userService = new UserService(userDao);
            List<User> registered = userService.getUserList();

            String userName = req.getParameter("username");

            boolean notOccupiedName = true;
            boolean emailAvailable = true;
            for (User usr : registered) {
                if (usr.getName().equals(userName)) {
                    notOccupiedName = false;
                    break;
                }else if (usr.getEmail().equals(req.getParameter("email"))) {
                    emailAvailable = false;
                    break;
                }
            }
            if (notOccupiedName && emailAvailable) {
                userService.addUser(req.getParameter("email"), req.getParameter("password"), userName, "Student");
                resp.sendRedirect("index.jsp");
            } else if (!notOccupiedName) {
                req.setAttribute("notAvailable", true);
                req.getRequestDispatcher("register.jsp").forward(req, resp);
            } else {
                req.setAttribute("emailTaken", true);
                req.getRequestDispatcher("register.jsp").forward(req, resp);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
