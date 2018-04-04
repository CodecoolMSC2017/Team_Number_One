package com.codecool.web.servlet;

import com.codecool.web.model.User;
import com.codecool.web.service.DataStorage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;

@WebServlet("/updateProfile")
public class ProfileUpdateServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User toBeUpdated = null;
        for (User user: DataStorage.getInstance().getUserList()) {
            if (user.getUniqueId().equals(req.getParameter("id"))) {
                toBeUpdated = user;
                break;
            }
        }

        toBeUpdated.setName(req.getParameter("userName"));
        toBeUpdated.setRole(req.getParameter("userRole"));

        JOptionPane.showMessageDialog(null, "User information has been updated!");

        req.setAttribute("profile", toBeUpdated);
        req.getRequestDispatcher("protected/userProfile.jsp").forward(req, resp);
    }

}
