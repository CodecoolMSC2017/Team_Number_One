package com.codecool.web.service;

import com.codecool.web.dao.DatabaseUserDao;
import com.codecool.web.dao.UserDao;
import com.codecool.web.model.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserService implements UserServiceInterface{

    private final UserDao userDao;

    public UserService(UserDao userDao){
        this.userDao=userDao;
    }

    public List<User> getUserList() {
        List<User> users = null;
        try {
            users = userDao.getAllUsers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }


    public void addUser(String email,String password,String name,String role){
        try {
            userDao.addUser(email,password,name,role);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(int id,String name,String role){
        try {
            userDao.updateUser(id,name,role);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public User getUserByName(String name) {
        User user = null;
        try {
            user = userDao.getUserByName(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<User> getStudents(){
        List<User> students = null;
        try {
            students = userDao.getAllUsers();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for(User u : students){
            if(u.getRole().equals("Mentor")){
                students.remove(u);
            }
        }
        return students;
    }

    @Override
    public void updateUser(User user, Connection connection) {
        UserDao usd = new DatabaseUserDao(connection);
        try {
            usd.updateUser(user.getUniqueId(), user.getName(), user.getRole());
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public User getUserById(int userId){
        User user = null;
        try {
            user =userDao.getUserById(userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
