package com.codecool.web.service;

import com.codecool.web.dao.QuestionDao;
import com.codecool.web.model.Question;

import java.sql.SQLException;
import java.util.List;

public class QuestionService {
    private QuestionDao questionDao;


    public QuestionService(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    public void saveQuestion(String question, String answer) {
        try {
            questionDao.addQuestion(question, answer);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Question> getAllQuestion() throws SQLException {
        return questionDao.findAll();
    }

    public Question findQuestionById(int id) throws SQLException {
        return questionDao.findQuestionById(id);

    }
}
