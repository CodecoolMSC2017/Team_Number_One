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
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/updateProfile")
public class ProfileUpdateServlet extends AbstractServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User toBeUpdated = null;
        try(Connection connection = getConnection(req.getServletContext())) {
            DataStorage DS = new DataStorage(connection);
            for (User user : DS.getUserList()) {
                if (Integer.toString(user.getUniqueId()) == req.getParameter("id")) {
                    toBeUpdated = user;
                    break;
                }
            }

            toBeUpdated.setName(req.getParameter("userName"));
            toBeUpdated.setRole(req.getParameter("userRole"));

            JOptionPane.showMessageDialog(null, "User information has been updated!");

            req.setAttribute("profile", toBeUpdated);
            req.getRequestDispatcher("protected/userProfile.jsp").forward(req, resp);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try(Connection connection = getConnection(req.getServletContext())) {
            DataStorage DS = new DataStorage(connection);
            if (req.getParameter("id").equals("My Scores")) {
                List<Result> results = new ArrayList<>();
                HttpSession session = req.getSession(false);
                User user = (User) session.getAttribute("user");

                for (Result result : DS.getAllResults()) {
                    if (result.getUser().getUniqueId() == user.getUniqueId()) {
                        results.add(result);
                    }
                }
                Statistics stat = new Statistics();
                req = stat.createChart(req);
                req.setAttribute("results", results);
                req.getRequestDispatcher("protected/score.jsp").forward(req, resp);
            }
            }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
}
