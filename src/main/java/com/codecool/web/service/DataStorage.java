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
    private List<User> allUsers = new ArrayList<User>();
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
        User user = null;
        for (User u:allUsers) {
            if(u.getName().equals("name")){
                user = u;
                break;
            }
        }
        return user;
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

    public List<User> getStudents(){
        List<User> students = new ArrayList<>();
        for (User u:allUsers) {
            if(u.getRole().equals("Student")){
                students.add(u);
            }
        }

        return students;
    }

    public List<Result> getAllResults() {
        return allResults;
    }

    public void addNewResult(Result result) {
        allResults.add(result);
    }
}