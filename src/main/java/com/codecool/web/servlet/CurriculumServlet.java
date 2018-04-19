package com.codecool.web.servlet;

import com.codecool.web.dao.DatabaseUserDao;
import com.codecool.web.dao.SubPageDao;
import com.codecool.web.dao.UserDao;
import com.codecool.web.model.AssignmentPage;
import com.codecool.web.model.SubPage;
import com.codecool.web.model.TextPage;
import com.codecool.web.model.User;
import com.codecool.web.service.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/curriculum")
public class CurriculumServlet extends AbstractServlet{


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AssignmentPage tempAssign = new AssignmentPage();
        req.getSession().setAttribute("sessTmpAssign", tempAssign);
        req.setAttribute("reqTmpAssign", tempAssign);
        if (req.getParameter("addPages").equals("Add Assignment")) {
            req.getRequestDispatcher("protected/addAssignment.jsp").forward(req, resp);
        }else if(req.getParameter("addPages").equals("Add Text Page")){
            req.getRequestDispatcher("protected/addTextPage.jsp").forward(req, resp);
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = getConnection(req.getServletContext())) {
            SubPageDao spDao = new SubPageDao(connection);
            SubPageService spService = new SubPageService(spDao);
            UserDao userDao = new DatabaseUserDao(connection);
            UserService userService = new UserService(userDao);
            AttendanceHandler AH = new AttendanceHandler(userService);

            List<SubPage> ds = spService.getAllSubPages();
            List<User> users = userService.getUserList();

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
                req.setAttribute("userList", AH.getStudentUserList());
                req.getRequestDispatcher("protected/attendance.jsp").forward(req, resp);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }
}