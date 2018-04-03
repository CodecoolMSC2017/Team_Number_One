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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User tempForCheck = new User(req.getParameter("username"), req.getParameter("password"));

        List<User> registered = DataStorage.getInstance().getUserList();
        List<SubPage> availablePages = new ArrayList<>();
        AvailablePages ap = new AvailablePages();

        if(registered.size() > 0 && registered.contains(tempForCheck)){
            String userID = null;
            for (User user: registered) {  //this is redundant, refractor later
                if (user.equals(tempForCheck)){
                    userID = user.getUniqueId();
                }
            }

            Cookie cookie = new Cookie("loginsession", userID != null ? userID : "0");
            cookie.setMaxAge(60*2); // 10 minutes before cookie is expired
            cookie.setHttpOnly(true);
            resp.addCookie(cookie);


            req.setAttribute("pageList", availablePages);
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
        resp.sendRedirect("index.html");
    }
}
