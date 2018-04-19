package com.codecool.web.servlet;

import com.codecool.web.dao.DatabaseUserDao;
import com.codecool.web.dao.UserDao;
import com.codecool.web.model.User;
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

@WebServlet("/userList")
public class UserListServlet extends AbstractServlet {


    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try(Connection connection = getConnection(req.getServletContext())) {
            UserDao userDao = new DatabaseUserDao(connection);
            UserService userService = new UserService(userDao);
            List<User> users = userService.getUserList();
            if (req.getParameter("id") != null) {
                for (User user : users) {
                    if (Integer.toString(user.getUniqueId()).equals(req.getParameter("id"))) {
                        req.setAttribute("profile", user);
                        req.getRequestDispatcher("protected/userProfile.jsp").forward(req, resp);
                    }
                }
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }
}
