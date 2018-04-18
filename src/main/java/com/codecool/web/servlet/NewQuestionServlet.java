package com.codecool.web.servlet;

import com.codecool.web.dao.QuestionDao;
import com.codecool.web.model.*;
import com.codecool.web.service.QuestionService;
import com.codecool.web.service.TempPageServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/addquestion")
public class NewQuestionServlet extends AbstractServlet {
    AssignmentPage tmpAssign;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //List<Question> questions;

        HttpSession session = req.getSession();
        TempPageServlet tmp = new TempPageServlet();
        AssignmentPage tmpAssign = (AssignmentPage) session.getAttribute("tempPage");

        tmpAssign = tmp.tempPageRefresh(req, tmpAssign);

        session.removeAttribute("tmpAssign");
        req.setAttribute("tmpAssign", tmpAssign);
        session.setAttribute("tmpAssign", tmpAssign);
        req.getRequestDispatcher("protected/addQuestion.jsp").forward(req, resp);
    }

}
