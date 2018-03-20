package com.codecool.web.servlet;

import com.codecool.web.model.User;
import com.codecool.web.service.DataStorage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
            //temporary for testing
            String yes = "<html><body>ok</body></html>";
            PrintWriter writer = response.getWriter();
            writer.println(yes);


        }
        else {
            //temporary for testing
            String no = "<html><body>wrong pw or name<br></body></html>";
            PrintWriter writer = response.getWriter();
            writer.println(no);
        }



    }
}
