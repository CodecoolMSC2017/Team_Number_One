package com.codecool.web.servlet;

import com.codecool.web.model.*;
import com.codecool.web.service.DataStorage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@WebServlet("/check-answers")
public class SendAssignment extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String answer = req.getParameter("answerGiven");
        int assignID = Integer.parseInt(req.getParameter("id"));
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        DataStorage.getInstance().getUser(user).addCompletedAssignment(assignID, answer);

    }
}



