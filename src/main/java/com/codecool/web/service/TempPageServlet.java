package com.codecool.web.service;

import com.codecool.web.model.AssignmentPage;
import com.codecool.web.model.Question;

import javax.servlet.http.HttpServletRequest;

public class TempPageServlet {

    public AssignmentPage tempPageRefresh(HttpServletRequest req, AssignmentPage temp) {
        AssignmentPage tempPage = temp;
        tempPage.setMaxScore(Integer.parseInt(req.getParameter("maxScore")));
        tempPage.setTitle(req.getParameter("assignTitle"));

        if (req.getParameter("question") != null && req.getParameter("answer") != null) {
            tempPage.addTask(new Question(0, req.getParameter("question"), req.getParameter("answer")));
        }

        return tempPage;
    }
}
