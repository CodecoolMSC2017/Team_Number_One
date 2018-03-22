package com.codecool.web.servlet;

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
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User tempForCheck = new User(request.getParameter("username"), request.getParameter("password"));

        DataStorage.getInstance().addList(new User("a", "a@a", "Mentor", "a")); //for testing, delete later
        DataStorage.getInstance().addSubPage(new TextPage("Test", "TestText"));

        List<User> registered = DataStorage.getInstance().getUserList();

        if(registered.size() > 0 && registered.contains(tempForCheck)){
            String userID = null;
            for (User user: registered) {  //this is redundant, refractor later
                if (user.equals(tempForCheck)){
                    userID = user.getUniqueId();
                    request.setAttribute("userID", userID);
                    request.setAttribute("userRole", user.getRole());
                }
            }
            Cookie cookie = new Cookie("loginsession", userID != null ? userID : "Failed_to_get_user_id");
            cookie.setMaxAge(60*2); // 2 minutes before cookie is expired
            cookie.setHttpOnly(true);
            response.addCookie(cookie);
            System.out.println(cookie.getName());
            request.setAttribute("pageList", DataStorage.getInstance().getAllSubPages());
            request.getRequestDispatcher("curriculum.jsp").forward(request, response);
        }

        else {
            PrintWriter out = response.getWriter();
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
