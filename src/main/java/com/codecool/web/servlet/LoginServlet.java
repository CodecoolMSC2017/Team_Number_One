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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User tempForCheck = new User(request.getParameter("username"), request.getParameter("password"));

        DataStorage.getInstance().addList(new User("a", "a@a", "Mentor", "a")); //for testing, delete later
        DataStorage.getInstance().addSubPage(new TextPage("Test", "TestText"));
        DataStorage.getInstance().addSubPage(new TextPage("Test2", "TestText2"));
        DataStorage.getInstance().getAllSubPages().get(1).setPublished();

        List<User> registered = DataStorage.getInstance().getUserList();
        List<SubPage> availablePages = new ArrayList<>();
        AvailablePages ap = new AvailablePages();

        if(registered.size() > 0 && registered.contains(tempForCheck)){
            String userID = null;
            for (User user: registered) {
                if (user.equals(tempForCheck)){
                    HttpSession session = request.getSession();
                    session.setAttribute("user", user);
                    session.setMaxInactiveInterval(30*60);
                    availablePages = ap.selectPages(user);
                }
            }
            request.setAttribute("pageList", availablePages);
            request.getRequestDispatcher("protected/curriculum.jsp").forward(request, response);
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
