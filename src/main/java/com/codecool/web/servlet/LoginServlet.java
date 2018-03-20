package com.codecool.web.servlet;

import com.codecool.web.model.User;
import com.codecool.web.service.DataStorage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User tempForCheck = new User(request.getParameter("username"), request.getParameter("password"));

        List<User> registered = DataStorage.getInstance().getUserList();



        if(registered.size() > 0 && registered.contains(tempForCheck)){
            //for testing
            Long userID = null;
            for (User user: registered) {  //this is redundant
                if (user.equals(tempForCheck)){
                    userID = user.getUniqueId();
                }
            }
            Cookie cookie = new Cookie("loginsession", userID != null ? userID.toString() : "0");
            cookie.setMaxAge(60*10); // 10 minutes before cookie is expired
            response.addCookie(cookie);
            request.getRequestDispatcher("test.jsp").forward(request, response);
            //test section end
        }

        else {
            //temporary for testing
            String no = "<html><head>" +
                    "<meta http-equiv=refresh content=1; />" +
                    "<head><body>" +
                    "<h2>Wrong password or user name!<br><h2>" +
                    "</body></html>";
            PrintWriter writer = response.getWriter();
            writer.println(no);
        }
    }


    // <meta http-equiv=refresh content=1; /> send a GET to to the servlet, what this method catch, nd redirect to the index.html
    // might need to find a more elegant way later
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("index.html");
    }
}
