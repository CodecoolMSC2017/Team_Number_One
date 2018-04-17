package com.codecool.web.servlet;

import com.codecool.web.model.Question;
import com.codecool.web.model.Result;
import com.codecool.web.model.User;
import com.codecool.web.service.DataStorage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

@WebServlet("/evaluation")
public class EvaluationServlet extends AbstractServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Result r = (Result) req.getSession().getAttribute("result");
        String assignTitle = r.getAssignmentPageTitle();
        String userName = r.getUser().getName();
        HashMap<Question, String> apResult = r.getUser().getListOfCompletedAssignments().get(r.getAssignmentPageId());
        int currentScore = r.getScore();

        req.getSession().setAttribute("result",r);
        req.setAttribute("result",r);
        req.setAttribute("map",apResult);
        req.setAttribute("assignTitle",assignTitle);
        req.setAttribute("userName",userName);
        req.setAttribute("score",currentScore);
        req.getRequestDispatcher("protected/evaluator.jsp").forward(req,resp);
    }
}
