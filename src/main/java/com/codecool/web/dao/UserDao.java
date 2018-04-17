package com.codecool.web.dao;

import com.codecool.web.model.Question;
import com.codecool.web.model.User;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public interface UserDao {

    List<User> getAllUsers() throws SQLException;

    User getUserById(int userId) throws SQLException;

    User getUserByName(String name) throws SQLException;

}
