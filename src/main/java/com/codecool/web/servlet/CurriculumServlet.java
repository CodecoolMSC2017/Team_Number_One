package com.codecool.web.servlet;

import com.codecool.web.model.AssignmentPage;
import com.codecool.web.model.SubPage;
import com.codecool.web.model.TextPage;
import com.codecool.web.model.User;
import com.codecool.web.service.AttendanceHandler;
import com.codecool.web.service.AvailablePages;
import com.codecool.web.service.DataStorage;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/curriculum")
public class CurriculumServlet extends HttpServlet {
    List<SubPage> ds = DataStorage.getInstance().getAllSubPages();
    List<User> users = DataStorage.getInstance().getUserList();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("addPages").equals("Add Assignment")) {
            req.getRequestDispatcher("protected/addAssignment.jsp").forward(req, resp);
        }else if(req.getParameter("addPages").equals("Add Text Page")){
            req.getRequestDispatcher("protected/addTextPage.jsp").forward(req, resp);
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getParameter("id") != null) {
            for (SubPage page : ds) {
                if (page.getId() == Integer.parseInt(req.getParameter("id"))) {
                    if (page instanceof TextPage) {
                        req.setAttribute("tp", page);
                        req.getRequestDispatcher("protected/displayTextPage.jsp").forward(req, resp);
                    } else {
                        req.setAttribute("ap", page);
                        req.getRequestDispatcher("protected/displayAssignPage.jsp").forward(req, resp);
                    }
                }
            }
        }

        if (req.getParameter("showUsers") != null) {
            req.setAttribute("users", users);
            req.getRequestDispatcher("protected/users.jsp").forward(req, resp);
        }

        if (req.getParameter("attendance") != null) {
            req.setAttribute("userList", AttendanceHandler.getStudentUserList());
            req.getRequestDispatcher("protected/attendance.jsp").forward(req, resp);
        }

        // for logout
        if (req.getParameter("logout") != null) {
            Cookie[] cookies = req.getCookies();
            List<User> users = DataStorage.getInstance().getUserList();
            if (cookies != null) {
                for (int i = 0; i < cookies.length; i++) {
                    if (cookies[i].getName().equals("loginsession")) {
                        cookies[i].setMaxAge(0);
                        resp.addCookie(cookies[i]);
                    }
                }
            }
            resp.sendRedirect("index.html");
        }
    }
}