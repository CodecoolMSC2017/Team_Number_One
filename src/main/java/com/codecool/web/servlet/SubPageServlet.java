package com.codecool.web.servlet;

import com.codecool.web.model.AssignmentPage;
import com.codecool.web.model.SubPage;
import com.codecool.web.model.TextPage;
import com.codecool.web.service.DataStorage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/sub-page")
public class SubPageServlet extends AbstractServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int subPageId = Integer.parseInt(req.getParameter("id"));
        SubPage tempSubPage = DataStorage.getInstance().getAllSubPages().get(subPageId);
        String dispatcher = "";

        if (tempSubPage instanceof AssignmentPage){
            AssignmentPage tempAssign = (AssignmentPage) tempSubPage;
            req.setAttribute("ap",tempAssign);
            dispatcher = "protected/displayAssignPage.jsp";

        } else if (tempSubPage instanceof TextPage){
            TextPage tempText = (TextPage) tempSubPage;
            req.setAttribute("tp",tempText);
            dispatcher = "protected/displayTextPage.jsp";
        }

        req.getRequestDispatcher(dispatcher).forward(req, resp);
    }
}
