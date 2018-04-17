package com.codecool.web.dao;

import com.codecool.web.dao.UserDao;
import com.codecool.web.model.Question;
import com.codecool.web.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class QuestionDao extends AbstractDao {

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

    public Question findQuestionById(int id) throws SQLException {
        String sql = "SELECT id, question, answer FROM questions WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return fetchQuestion(resultSet);
                }
            }
        }
        return null;
    }

    @Deprecated
    public Question addQuestion(Question question) throws SQLException {
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        String sql = "INSERT INTO questions (question, answer) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            statement.setString(1, question.getQuestion());
            statement.setString(2,question.getAnswer());
            executeInsert(statement);
            int id = fetchGeneratedId(statement);
            return new Question(id, question.getQuestion(), question.getAnswer());
        } catch (SQLException ex) {
            connection.rollback();
            throw ex;
        } finally {
            connection.setAutoCommit(autoCommit);
        }
    }

    public Question addQuestion(String question, String answer) throws SQLException {
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        String sql = "INSERT INTO questions (question, answer) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            statement.setString(1, question);
            statement.setString(2, answer);
            executeInsert(statement);
            int id = fetchGeneratedId(statement);
            return new Question(id, question, answer);
        } catch (SQLException ex) {
            connection.rollback();
            throw ex;
        } finally {
            connection.setAutoCommit(autoCommit);
        }
    }

    private Question fetchQuestion(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String question = resultSet.getString("question");
        String answer = resultSet.getString("answer");
        return new Question(id, question, answer);
    }

}
