package com.codecool.web.service;

import com.codecool.web.dao.QuestionDao;
import com.codecool.web.dao.SubPageDao;
import com.codecool.web.model.AssignmentPage;
import com.codecool.web.model.Question;
import com.codecool.web.model.SubPage;
import com.codecool.web.model.TextPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SubPageServiceTest {

    private String user = System.getProperty("user");
    private String password = System.getProperty("password");
    private String database = System.getProperty("database");
    private String url = "jdbc:postgresql://localhost/" + database + "?user=" + user + "&password=" + password;
    private Connection connection;

    {
        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    SubPageDao subPageDao = new SubPageDao(connection);
    SubPageService subPageService = new SubPageService(subPageDao);
    QuestionDao questionDao = new QuestionDao(connection);
    QuestionService questionService = new QuestionService(questionDao);

    @BeforeEach
    void setUp() throws SQLException {
        String sql = "DROP TABLE IF EXISTS subpages;" + "DROP TABLE IF EXISTS questions";
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
        String sqlInit = "CREATE TABLE subpages (" +
                "id SERIAL PRIMARY KEY," +
                "title TEXT NOT NULL," +
                "type TEXT NOT NULL," +
                "description TEXT,\n" +
                "questionid INTEGER[]," +
                "maxscore INTEGER," +
                "published BOOLEAN);" +
                "CREATE TABLE questions (" +
                "id SERIAL PRIMARY KEY," +
                "question TEXT NOT NULL," +
                "answer TEXT NOT NULL);";
        statement.executeUpdate(sqlInit);
        String sqlInsertions = "INSERT INTO subpages (title, type, description, published) values ('TestText1', 'T', 'Test text', true);" +
                "INSERT INTO questions (question, answer) VALUES ('Are you here?', 'yes'), ('Are you not here?','no');" +
                "INSERT INTO subpages (title, type, questionid, maxscore, published)\n" +
                "VALUES ('The big questions','A',(SELECT ARRAY_AGG(questions.id) FROM questions GROUP BY questions.id ORDER BY questions.id LIMIT 1),20,true);";
        statement.executeUpdate(sqlInsertions);

    }

    @Test
    void getAllSubPages() throws SQLException {
        List<SubPage> excepted = new ArrayList<>();
        List<Question> questions = new ArrayList<>();
        excepted.add(new TextPage(1, "TestText1", true, "Test text"));
        questions.add(new Question(1,"Are you here?", "yes"));
        questions.add(new Question(2, "Are you here?", "no"));
        excepted.add(new AssignmentPage(2, "The big questions", true, questions, 20));
        List<SubPage> actual = subPageService.getAllSubPages();

        for (int i = 0; i < excepted.size(); i++) {
            Assertions.assertEquals(actual.get(i).getId(),excepted.get(i).getId());
        }

    }

    @Test
    void getSubPageById() throws SQLException {
        Assertions.assertEquals(1, subPageService.getSubPageById(1).getId());
    }

    @Test
    void setPublished() {
    }

    @Test
    void setUnPublished() {
    }
}