package com.codecool.web.servlet;

import com.codecool.web.model.AssignmentPage;
import com.codecool.web.model.SubPage;
import com.codecool.web.model.TextPage;
import com.codecool.web.model.User;
import com.codecool.web.service.DataStorage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/curriculum")
public class CurriculumServlet extends HttpServlet {
    List<SubPage> ds = DataStorage.getInstance().getAllSubPages();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("newText") != null) {
            request.getRequestDispatcher("displayAssignPage.jsp").forward(request, response);
        }
        if (request.getParameter("newAssignment") != null) {
            request.getRequestDispatcher("displayTextPage.jsp").forward(request, response);
        }
        if (request.getParameter("addPages") != null) {
            request.getRequestDispatcher("addPages.jsp").forward(request, response);
        }/* else {
             for (SubPage sp : ds) {
                if (sp.getId() == Integer.parseInt(request.getParameter("id"))) {
                    if (sp instanceof TextPage) {
                        response.sendRedirect("displayTextPage.html");
                    } else if (sp instanceof AssignmentPage) {
                        response.sendRedirect("displayAssignPage.html");
                    }
                }
            }

        }*/
    }


    // for logout
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        List<User> users = DataStorage.getInstance().getUserList();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                    cookies[i].setMaxAge(0);
                    resp.addCookie(cookies[i]);
            }
        }
        resp.sendRedirect("index.html");
    }
}
