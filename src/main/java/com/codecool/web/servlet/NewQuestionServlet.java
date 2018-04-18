package com.codecool.web.servlet;

import com.codecool.web.dao.QuestionDao;
import com.codecool.web.model.*;
import com.codecool.web.service.QuestionService;

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

        String assignTitle=req.getParameter("assignTitle");
        int maxScore = Integer.parseInt(req.getParameter("maxScore"));
        String question = req.getParameter("question");
        String answer = req.getParameter("answer");
        HttpSession session = req.getSession();

        try (Connection connection = getConnection(req.getServletContext())){
            QuestionDao questionDao = new QuestionDao(connection);
            QuestionService questionService = new QuestionService(questionDao);

            questionService.saveQuestion(question, answer);


        } catch (SQLException e) {
            e.printStackTrace();
        }


        if(!req.getParameterMap().containsKey("alreadySubmittedQuestion")){

            //questions = new ArrayList<>();


            //questions.add(new Question(question,new Answer(answer)));
            tmpAssign = new AssignmentPage(assignTitle,questions,maxScore);
            req.setAttribute("tmpAssign",tmpAssign);
            session.setAttribute("tmpAssign",tmpAssign);
            req.getRequestDispatcher("protected/addQuestion.jsp").forward(req,resp);
        }else{

            questions=tmpAssign.getListOfQuestions();
            String question = req.getParameter("question");
            String answer = req.getParameter("answer");
            questions.add(new Question(question,new Answer(answer)));

            req.setAttribute("tmpAssign",tmpAssign);
            session.setAttribute("tmpAssign",tmpAssign);
            req.getRequestDispatcher("protected/addQuestion.jsp").forward(req,resp);

        }



    }


    //for the back bottom
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
