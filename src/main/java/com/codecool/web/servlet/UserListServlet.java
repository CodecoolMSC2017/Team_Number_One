package com.codecool.web.servlet;

import com.codecool.web.model.User;
import com.codecool.web.service.DataStorage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/userList")
public class UserListServlet extends AbstractServlet {
    List<User> users = DataStorage.getInstance().getUserList();

    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("id") != null) {
            for (User user : users) {
                if (user.getUniqueId().equals(req.getParameter("id"))) {
                    req.setAttribute("profile", user);
                    req.getRequestDispatcher("protected/userProfile.jsp").forward(req, resp);
                }
            }
        }
    }
}
