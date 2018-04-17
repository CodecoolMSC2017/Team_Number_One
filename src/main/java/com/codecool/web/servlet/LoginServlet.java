package com.codecool.web.servlet;

import com.codecool.web.model.SubPage;
import com.codecool.web.model.TextPage;
import com.codecool.web.model.User;
import com.codecool.web.service.AvailablePages;
import com.codecool.web.service.DataStorage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User tempForCheck = new User(req.getParameter("username"), req.getParameter("password"));
        Connection connection = (Connection) req.getServletContext();
        DataStorage DS = new DataStorage(connection);
        List<User> registered = DS.getUserList();
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
    }


    // <meta http-equiv=refresh content=1; /> send a GET to to the servlet, what this method catch, nd redirect to the index.html
    // might need to find a more elegant way later
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        resp.sendRedirect("index.html");
        //req.getRequestDispatcher("index.html").forward(req, resp);
    }
}
