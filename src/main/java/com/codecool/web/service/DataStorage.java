package com.codecool.web.service;

import com.codecool.web.dao.DatabaseUserDao;
import com.codecool.web.dao.UserDao;
import com.codecool.web.model.*;
import org.joda.time.LocalDate;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataStorage {
    private List<SubPage> allSubPages = new ArrayList<>();
    private List<Result> allResults = new ArrayList<>();
    private Connection connection;

    public DataStorage(Connection connection){
        this.connection=connection;
    }

    public List<User> getUserList() {
        UserDao UD = new DatabaseUserDao(connection);
        List<User> users = null;
        try {
            users = UD.getAllUsers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }


    public void addUser(String email,String password,String name,String role){
        UserDao UD = new DatabaseUserDao(connection);
        try {
            UD.addUser(email,password,name,role);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public User getUserByName(String name) {
        UserDao UD = new DatabaseUserDao(connection);
        User user = null;
        try {
            user = UD.getUserByName(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<User> getStudents(){
        UserDao UD = new DatabaseUserDao(connection);
        List<User> students = null;
        try {
            students = UD.getAllUsers();
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


    public void addSubPage(SubPage subPage) {

        int id = allSubPages.size();
        subPage.setId(id);
        allSubPages.add(subPage);
    }

    public List<SubPage> getAllSubPages() { return allSubPages; }



    public SubPage getSubPageById(int id){
        SubPage result = null;
        for (SubPage temp:allSubPages) {
            if(temp.getId() == id){
                result = temp;
                break;
            }
        }
        return result;
    }



    public List<Result> getAllResults() {
        return allResults;
    }

    public void addNewResult(Result result) {
        allResults.add(result);
    }
}