package com.codecool.web.servlet;

import com.codecool.web.dao.DatabaseUserDao;
import com.codecool.web.dao.UserDao;
import com.codecool.web.model.User;
import com.codecool.web.service.RegisterService;
import com.codecool.web.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/register")
public class RegisterServlet extends AbstractServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try(Connection connection = getConnection(req.getServletContext())) {
            UserDao userDao = new DatabaseUserDao(connection);
            UserService userService = new UserService(userDao);
            RegisterService rs = new RegisterService(connection);

            String userName = req.getParameter("username");
            String userEmail = req.getParameter("email");

            boolean isNameFree = rs.isNotRegisteredName(userName);

            if(isNameFree && rs.isNotRegisteredEmail(userEmail)){
                userService.addUser(userEmail, req.getParameter("password"), userName,"Student");
                resp.sendRedirect("index.jsp");
            }
            else if (!(isNameFree)){
                req.setAttribute("notAvailable", "This user name is already taken!");
                req.getRequestDispatcher("register.jsp").forward(req, resp);
            }
            else {
                req.setAttribute("notAvailable", "This email is already registered!");
                req.getRequestDispatcher("register.jsp").forward(req, resp);
            }


        }catch(SQLException ex){
            ex.printStackTrace();
        }



        }
    }
