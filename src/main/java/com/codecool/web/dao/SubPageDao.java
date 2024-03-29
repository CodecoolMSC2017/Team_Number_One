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

    public List<SubPage> findAllSubPages() throws SQLException {
        List<SubPage> pages = new ArrayList<>();
        String sql = "SELECT * FROM subpages ORDER BY id";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                pages.add(fetchSubPage(resultSet));
            }
        } catch (SQLException er) {
            er.printStackTrace();
        }
        return pages;
    }

    public SubPage findSubPageById(int id) throws SQLException {
        SubPage sb = null;
        String sql = "SELECT * FROM subpages WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                sb = fetchSubPage(resultSet);
            }
        }
        return sb;
    }


    public int addTextPage(String title, String description) throws SQLException {
        String sql = "INSERT INTO subpages (title, description, published, type) VALUES (?, ?, 'false', 'T')";
        connection.setAutoCommit(false);

        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            statement.setString(1, title);
            statement.setString(2, description);
            executeInsert(statement);
            int id = fetchGeneratedId(statement);
            connection.commit();
            return id;
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        }finally {
            connection.setAutoCommit(true);
        }
    }


    public int addAssignmentPage(String title, int maxscore, List<Question> questions) throws SQLException {
        QuestionDao qd = new QuestionDao(connection);
        connection.setAutoCommit(false);
        String sql = "INSERT INTO subpages (title, maxscore, questionid, published, type) VALUES (?, ?, ?, false, 'A')";

        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            List<Question> quids = new ArrayList<>();

            for (Question question : questions) {
                Question finalQuestion = qd.addQuestion(question.getQuestion(), question.getAnswer());
                quids.add(finalQuestion);
            }

            Array ids = getQuestionIds(quids);
            statement.setString(1, title);
            statement.setInt(2, maxscore);
            statement.setArray(3, ids);
            executeInsert(statement);
            int id = fetchGeneratedId(statement);
            System.out.println(id);
            connection.commit();
            System.out.println(id);
            return id;
        } catch (SQLException ex) {
            connection.rollback();
            throw ex;
        } finally {
            connection.setAutoCommit(true);
        }
    }


    private SubPage fetchSubPage(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String title = resultSet.getString("title");
        Boolean published = resultSet.getBoolean("published");
        String type = resultSet.getString("type");
        SubPage page = null;

        if (type.equals("T")) {
            String description = resultSet.getString("description");
            page = new TextPage(id, title, published, description);
        } else if (type.equals("A")) {
            int maxscore = resultSet.getInt("maxscore");
            Array qids = resultSet.getArray("questionid");
            List<Question> questions = convertToQuestions(qids);
            page = new AssignmentPage(id, title, published, questions, maxscore);
        }
        return page;
    }

    private List<Question> convertToQuestions(Array qids) throws SQLException {
        QuestionDao qd = new QuestionDao(connection);

        Integer[] ids = (Integer[])qids.getArray();
        List<Question> questions = new ArrayList<>();
        for (Integer num : ids) {
            questions.add(qd.findQuestionById(num));
        }
        return questions;
    }


    private Array getQuestionIds(List<Question> questions) throws SQLException {
        List<Integer> quids = new ArrayList<>();
        for (Question question : questions) {
            quids.add(question.getId());
        }
        Object[] tempArray = quids.toArray();
        Array ids = connection.createArrayOf("INTEGER", tempArray);
        return ids;
    }

    public void setPublished(int pageId){
        String sql = "UPDATE subpages SET published=true " +
                "WHERE id=?";

        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            statement.setInt(1, pageId);
            statement.executeQuery();
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void setUnPublished(int pageId){
        String sql = "UPDATE subpages SET published=false " +
                "WHERE id=?";

        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            statement.setInt(1, pageId);
            statement.executeQuery();
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
