package com.codecool.web.servlet;

import com.codecool.web.model.Result;
import com.codecool.web.model.Statistics;
import com.codecool.web.model.User;
import com.codecool.web.service.DataStorage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/updateProfile")
public class ProfileUpdateServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User toBeUpdated = null;
        for (User user : DataStorage.getInstance().getUserList()) {
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


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getParameter("id").equals("My Scores")) {
            List<Result> results = new ArrayList<>();
            HttpSession session = req.getSession(false);
            User user = (User)session.getAttribute("user");

            for (Result result : DataStorage.getInstance().getAllResults()) {
                if (result.getUser().getUniqueId().equals(user.getUniqueId())) {
                    results.add(result);
                }
            }
            Statistics stat = new Statistics();
            req = stat.createChart(req);
            req.setAttribute("results", results);
            req.getRequestDispatcher("protected/score.jsp").forward(req, resp);
        }
    }
}
