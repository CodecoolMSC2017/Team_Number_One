package com.codecool.web.service;

import com.codecool.web.dao.DatabaseUserDao;
import com.codecool.web.dao.UserDao;
import com.codecool.web.model.User;

import java.sql.Connection;
import java.util.List;

public class RegisterService {
    private UserDao userDao;
    private UserService userService;
    private List<User> registered;

    public RegisterService(Connection connection) {
        this.userDao = new DatabaseUserDao(connection);
        userService = new UserService(userDao);
        registered = userService.getUserList();
    }

    public boolean isNotRegisteredName(String userName){
        for (User usr : registered) {
            if (usr.getName().equals(userName)) {
                return false;
            }
        }
        return true;
    }

    public boolean isNotRegisteredEmail(String userEmail){
        for (User usr : registered) {
            if (usr.getEmail().equals(userEmail)) {
                return false;
            }
        }
        return true;
    }
}
