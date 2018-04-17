package com.codecool.web.servlet;

import com.codecool.web.model.Result;
import com.codecool.web.model.User;
import com.codecool.web.service.DataStorage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/listOfSubmissions")
public class SubmissionsServlet extends AbstractServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getSession().setAttribute("results", DataStorage.getInstance().getAllResults());
        req.getRequestDispatcher("protected/submissionsList.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Result r = (Result) req.getSession().getAttribute("result");
        int score = Integer.parseInt(req.getParameter("score"));

        r.setScore(score);

        req.getRequestDispatcher("protected/submissionsList.jsp").forward(req,resp);
    }
}
