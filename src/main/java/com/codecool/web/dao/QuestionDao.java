package com.codecool.web.dao;

import com.codecool.web.dao.UserDao;
import com.codecool.web.model.Question;
import com.codecool.web.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class QuestionDao extends AbstractDao {
    private String inserSql = "INSERT INTO questions(id, question, answer) VALUES(?, ?, ?);"

    public QuestionDao(Connection connection) {
        super(connection);
    }

    public List<Question> findAll() throws SQLException {
        String sql = "SELECT id, question, answer FROM questions";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            List<Question> questions = new ArrayList<>();
            while (resultSet.next()) {
                questions.add(fetchQuestion(resultSet));
            }
            return questions;
        }
    }

    private Question fetchQuestion(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String question = resultSet.getString("question");
        String answer = resultSet.getString("answer");
        return new Question(id, question, answer);
    }


    public void addQuestion(Question question) throws SQLException {
        try( PreparedStatement ps = connection.prepareStatement(inserSql)){
            ps.setInt(1,question.getId());
            ps.setString(2, question.getQuestion());
            ps.setString(3, question.getAnswer());
            executeInsert(ps);
        }
    }
}
