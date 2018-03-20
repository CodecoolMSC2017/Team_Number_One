package com.codecool.web.servlet;

import com.codecool.web.model.AssignmentPage;
import com.codecool.web.service.DataStorage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.io.IOException;

@WebServlet("/assignment-page")
public class AssignmentPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int assignId = Integer.parseInt(req.getParameter("id"));
        AssignmentPage tempAssign = DataStorage.getInstance().getAllAssignmentPages().get(assignId);
        req.setAttribute("ap",tempAssign);
        req.getRequestDispatcher("displayAssignPage.jsp").forward(req, resp);
    }
}
