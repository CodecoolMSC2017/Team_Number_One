package com.codecool.web.servlet;

import com.codecool.web.dao.SubPageDao;
import com.codecool.web.model.AssignmentPage;
import com.codecool.web.model.SubPage;
import com.codecool.web.model.TextPage;
import com.codecool.web.service.SubPageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/sub-page")
public class SubPageServlet extends AbstractServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = getConnection(req.getServletContext())) {
            int subPageId = Integer.parseInt(req.getParameter("id"));
            SubPageDao spDao = new SubPageDao(connection);
            SubPageService spService = new SubPageService(spDao);
            SubPage tempSubPage = spService.getAllSubPages().get(subPageId);
            String dispatcher = "";

            if (tempSubPage instanceof AssignmentPage) {
                AssignmentPage tempAssign = (AssignmentPage) tempSubPage;
                req.setAttribute("ap", tempAssign);
                dispatcher = "protected/displayAssignPage.jsp";

            } else if (tempSubPage instanceof TextPage) {
                TextPage tempText = (TextPage) tempSubPage;
                req.setAttribute("tp", tempText);
                dispatcher = "protected/displayTextPage.jsp";
            }

            req.getRequestDispatcher(dispatcher).forward(req, resp);
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }
}
