package com.codecool.web.servlet;

import com.codecool.web.model.*;
import com.codecool.web.service.DataStorage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

@WebServlet("/check-answers")
public class SendAssignment extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        int assignId = Integer.parseInt(req.getParameter("id"));
        AssignmentPage tempAss = (AssignmentPage) DataStorage.getInstance().getSubPageById(assignId);
        HashMap<Question, String> tempHash = new HashMap<>();
        for (Question q:tempAss.getListOfQuestions()) {
            tempHash.put(q,req.getParameter(q.getAnswer().getAnswer()));
        }
        user.addCompletedAssignment(assignId,tempHash);
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        Result result = new Result(assignId,user,ts);
        DataStorage.getInstance().addNewResult(result);

        req.setAttribute("pageList", DataStorage.getInstance().getAllSubPages());
        req.setAttribute("isSucces",true);
        req.getRequestDispatcher("protected/curriculum.jsp").forward(req,resp);
    }
}



