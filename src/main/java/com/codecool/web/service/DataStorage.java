package com.codecool.web.service;

import com.codecool.web.model.*;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataStorage {
    private List<User> allUsers = new ArrayList<User>();
    private List<SubPage> allSubPages = new ArrayList<>();
    private List<Result> allResults = new ArrayList<>();

    private DataStorage() {
        allUsers.add(new User("a", "a@a", "Mentor", "a"));
        allUsers.add(new User("s","s@s","Student", "s"));  //test student user
        allUsers.add(new User("s2","s@s","Student", "s2"));
        allUsers.add(new User("cekil","s@s","Student", "q"));
        allUsers.add(new User("cecil","s@s","Student", "c"));
        allUsers.get(1).getAttendance().setAttendacePerDays(new LocalDate(), true); //for attednace testing
        allUsers.get(2).getAttendance().setAttendacePerDays(new LocalDate(), false);
        allUsers.get(3).getAttendance().setAttendacePerDays(new LocalDate(), true);
        allUsers.get(4).getAttendance().setAttendacePerDays(new LocalDate(), true);
        allSubPages.add(new TextPage("Test", "TestText"));
    }

    private static DataStorage ourInstance = new DataStorage();

    public static DataStorage getInstance() {
        return ourInstance;
    }

    public void addList(User user){
        allUsers.add(user);
    }

    public List<User> getUserList(){
        return allUsers;
    }

    public void addSubPage(SubPage subPage) {

        int id = allSubPages.size();
        subPage.setId(id);
        allSubPages.add(subPage);
    }

    public List<SubPage> getAllSubPages() { return allSubPages; }

    public User getUser(User u){
        User result = null;
        for (User tempUser:allUsers) {
            if(tempUser.getUniqueId().equals(u.getUniqueId())){
                result = tempUser;
                break;
            }
        }
        return result;
    }

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