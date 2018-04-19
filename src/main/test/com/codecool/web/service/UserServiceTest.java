package com.codecool.web.service;

import com.codecool.web.dao.DatabaseUserDao;
import com.codecool.web.dao.UserDao;
import com.codecool.web.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

class UserServiceTest {

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

    UserDao userDao = new DatabaseUserDao(connection);
    UserService userService = new UserService(userDao);

    @BeforeEach
    void setUp() throws SQLException {
        String sql = "DROP TABLE IF EXISTS users;";
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
        String sqlinit = "CREATE TABLE users (" +
                "    id SERIAL PRIMARY KEY," +
                "    email TEXT UNIQUE NOT NULL," +
                "    password TEXT NOT NULL," +
                "    username TEXT NOT NULL," +
                "    userrole TEXT NOT NULL);";
        statement.executeUpdate(sqlinit);
        String sqlInsertions = "INSERT INTO users (email, password, username, userrole) VALUES" +
                "('a@a', 'a', 'a', 'Mentor')," +
                "('b@b', 'b', 'b', 'Student')," +
                "('c@c', 'c', 'c', 'Mentor')," +
                "('d@d', 'd', 'd', 'Student')";
        statement.executeUpdate(sqlInsertions);

    }

    @Test
    void getUserList() {
        List<User> excepted = new ArrayList<>();
        excepted.add(new User(1,"a","a@a","Mentor","a"));
        excepted.add(new User(2,"b","b@b","Student","b"));
        excepted.add(new User(3,"c","c@c","Mentor","c"));
        excepted.add(new User(4,"b","d@d","Student","d"));
        List<User> actual = userService.getUserList();
        userService.addUser("e@e","e","e","Student");

        for (int i = 0; i < excepted.size(); i++) {
            Assertions.assertEquals(actual.get(i).getUniqueId(),excepted.get(i).getUniqueId());
        }
    }

    @Test
    void getUserByName() {
        Assertions.assertEquals(3, userService.getUserByName("c").getUniqueId());
    }

    @Test
    void getStudents() {
        List<User> excepted = new ArrayList<>();
        excepted.add(new User(2,"b","b@b","Student","b"));
        excepted.add(new User(4,"b","d@d","Student","d"));
        List<User> actual = userService.getStudents();

        for (int i = 0; i < excepted.size(); i++) {
            Assertions.assertEquals(actual.get(i).getUniqueId(),excepted.get(i).getUniqueId());
        }
    }

    @Test
    void getUserById() {
        Assertions.assertEquals(4,userService.getUserById(4).getUniqueId());
    }
}