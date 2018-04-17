package com.codecool.web.service;

import com.codecool.web.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserServiceInterface {

    List<User> getUserList()throws SQLException;

    void addUser(String email,String password,String name,String role)throws SQLException;

    User getUserByName(String name)throws SQLException;

    User getUserById(int userId)throws SQLException;

    List<User> getStudents()throws SQLException;


}
