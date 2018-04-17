package com.codecool.web.dao;

import com.codecool.web.model.AssignmentPage;
import com.codecool.web.model.Question;
import com.codecool.web.model.SubPage;
import com.codecool.web.model.TextPage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class SubPageDao extends AbstractDao {

    public SubPageDao(Connection connection) {
        super(connection);
    }

    public List<SubPage> findAll() throws SQLException {
        String sql = "SELECT id, question, answer FROM questions";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            List<SubPage> pages = new ArrayList<>();
            while (resultSet.next()) {
                pages.add(fetchSubPage(resultSet));
            }
            return pages;
        }
    }


    public int addTextPage(String title, String description) throws SQLException {
        String sql = "INSERT INTO subpages (title, description, published, type) VALUES (?, ?, 'false', 'TEXT')";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            statement.setString(1, title);
            statement.setString(2, description);
            executeInsert(statement);
            int id = fetchGeneratedId(statement);
            connection.commit();
            return id;
        }
    }


    public int addAssignmentPage(String title, int maxscore, List<Question> questions) throws SQLException {
        Array ids= getQuestionIds(questions);
        String sql = "INSERT INTO subpages (title, maxscore, ids, published, type) VALUES (?, ?, ?, 'false', 'TEXT')";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            statement.setString(1, title);
            statement.setInt(2, maxscore);
            statement.setArray(3, ids);
            executeInsert(statement);
            int id = fetchGeneratedId(statement);
            connection.commit();
            return id;
        }
    }


    private SubPage fetchSubPage(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String title = resultSet.getString("title");
        Boolean published = resultSet.getBoolean("published");
        String type = resultSet.getString("type");
        SubPage page = null;
        if (type.equals("Text")) {
            String description = resultSet.getString("description");
            page = new TextPage(id, title, published, description);
        }else if (type.equals("Assignment")){
            int maxscore = resultSet.getInt("maxscore");
            Array qids = resultSet.getArray("questionid");
            Integer[] ids = (Integer[])qids.getArray();
            List<Question> questions = convertToQuestions(qids);
            page = new AssignmentPage(id, title, published, questions, maxscore);
        }
        return page;
    }

    private List<Question> convertToQuestions(Array qids) throws SQLException {
        QuestionDao qd = new QuestionDao(connection);

        Integer[] ids = (Integer[])qids.getArray();
        List<Question> questions = null;
        for (Integer num : ids) {
            questions.add(qd.findQuestionById(num));
        }
        return questions;
    }


    private Array getQuestionIds(List<Question> questions) throws SQLException {
        List<Integer> quids = null;
        for(Question question : questions) {
            quids.add(question.getId());
        }
        Object[] tempArray = quids.toArray();
        Array ids = connection.createArrayOf("INTEGER", tempArray);
        return ids;
    }
}
