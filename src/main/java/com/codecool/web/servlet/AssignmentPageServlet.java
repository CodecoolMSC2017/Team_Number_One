package com.codecool.web.servlet;

import com.codecool.web.dao.SubPageDao;
import com.codecool.web.model.AssignmentPage;
import com.codecool.web.service.SubPageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/assignment-page")
public class AssignmentPageServlet extends AbstractServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int assignId = Integer.parseInt(req.getParameter("id"));
        try (Connection connection = getConnection(req.getServletContext())) {
            SubPageDao spDao = new SubPageDao(connection);
            SubPageService spService = new SubPageService(spDao);
            AssignmentPage tempAssign = (AssignmentPage) spService.getAllSubPages().get(assignId);
            req.setAttribute("ap", tempAssign);
            req.getRequestDispatcher("protected/displayAssignPage.jsp").forward(req, resp);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
