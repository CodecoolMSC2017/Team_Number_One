package com.codecool.web.servlet;

import com.codecool.web.model.*;
import com.codecool.web.service.TempPageServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/addquestion")
public class NewQuestionServlet extends AbstractServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //List<Question> questions;

        HttpSession session = req.getSession();
        TempPageServlet tmp = new TempPageServlet();
        AssignmentPage tmpAssign = (AssignmentPage) session.getAttribute("sessTmpAssign");

        tmpAssign = tmp.tempPageRefresh(req, tmpAssign);
        System.out.println(tmpAssign.getListOfQuestions().get(0).getQuestion());

        session.removeAttribute("sessTmpAssign");
        req.setAttribute("reqTmpAssign", tmpAssign);
        session.setAttribute("sessTmpAssign", tmpAssign);
        req.getRequestDispatcher("protected/addAssignment.jsp").forward(req, resp);
    }

}
