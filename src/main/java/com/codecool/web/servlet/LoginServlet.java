package com.codecool.web.servlet;

import com.codecool.web.dao.DatabaseUserDao;
import com.codecool.web.dao.UserDao;
import com.codecool.web.model.SubPage;
import com.codecool.web.model.TextPage;
import com.codecool.web.model.User;
import com.codecool.web.service.*;
import com.codecool.web.service.UserNotRegisteredException;
import com.codecool.web.service.exceptions.NoUserRegisteredException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/loginServlet")
public class LoginServlet extends AbstractServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DataSource dataSource = (DataSource) req.getServletContext().getAttribute("dataSource");

        try (Connection connection = dataSource.getConnection()) {
            UserDao userDao = new DatabaseUserDao(connection);
            String userName = req.getParameter("username");
            String passw = req.getParameter("password");

            LoginService loginService = new LoginService(userName, passw, userDao);
            try {
                User user = loginService.fetchUser();
                req.getSession().setAttribute("user", user);

                //put available pages here
                
                req.getRequestDispatcher("protected/curriculum.jsp").forward(req, resp);


            } catch (UserNotRegisteredException e) {
                req.setAttribute("error", "Wrong password of user name!");
                req.getRequestDispatcher("index.jsp").forward(req, resp);
            } catch (NoUserRegisteredException e) {
                req.setAttribute("error", "No user registered yet!");
                req.getRequestDispatcher("index.jsp").forward(req, resp);
            }


        }
        catch (SQLException e) {
            e.printStackTrace();
        }


        /*
        User tempForCheck = new User(req.getParameter("username"), req.getParameter("password"));
        try(Connection connection = getConnection(req.getServletContext())) {
            DataStorage DS = new DataStorage(connection);
            List<User> registered = DS.getUserList();
            List<SubPage> availablePages = new ArrayList<>();
            AvailablePages ap = new AvailablePages();

            if (registered.size() > 0 && registered.contains(tempForCheck)) {
                String userID = null;
                for (User user : registered) {
                    if (user.equals(tempForCheck)) {
                        HttpSession session = req.getSession();
                        session.setAttribute("user", user);
                        session.setMaxInactiveInterval(30 * 60);
                        availablePages = ap.selectPages(user);
                    }
                }
                req.getSession(false).setAttribute("pageList", availablePages);
                req.getRequestDispatcher("protected/curriculum.jsp").forward(req, resp);
            } else {
                PrintWriter out = resp.getWriter();
                out.println("<html><body><script>alert('Wrong username or password!');window.location.href = \"index.jsp\"</script></body></html>");
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        */
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        resp.sendRedirect("index.jsp");
    }
}
