package com.codecool.web.servlet;

import com.codecool.web.dao.UserDao;
import com.codecool.web.service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import com.codecool.web.dao.UserDao;
import com.codecool.web.model.User;
import com.codecool.web.service.LoginService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DataSource dataSource = (DataSource) req.getServletContext().getAttribute("dataSource");

        try (Connection connection = dataSource.getConnection()) {

            String userName = req.getParameter("username");
            String passw = req.getParameter("password");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }


        /*
        User tempForCheck = new User(req.getParameter("username"), req.getParameter("password"));

        List<User> registered = DataStorage.getInstance().getUserList();
        List<SubPage> availablePages = new ArrayList<>();
        AvailablePages ap = new AvailablePages();

        if(registered.size() > 0 && registered.contains(tempForCheck)){
            String userID = null;
            for (User user: registered) {
                if (user.equals(tempForCheck)){
                    HttpSession session = req.getSession();
                    session.setAttribute("user", user);
                    session.setMaxInactiveInterval(30*60);
                    availablePages = ap.selectPages(user);
                }
            }
            req.getSession(false).setAttribute("pageList", availablePages);
            req.getRequestDispatcher("protected/curriculum.jsp").forward(req, resp);
        }

        else {
            PrintWriter out = resp.getWriter();
            out.println ("<html><body><script>alert('Wrong username or password!');window.location.href = \"index.html\"</script></body></html>");
        }
        */
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        resp.sendRedirect("index.html");
    }
}
