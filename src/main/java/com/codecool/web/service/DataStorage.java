package com.codecool.web.service;

import com.codecool.web.model.*;
import org.joda.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DataStorage {
    private List<User> allUsers = new ArrayList<User>();
    private List<SubPage> allSubPages = new ArrayList<>();

    private DataStorage() {
        // for testing
        allUsers.add(new User("a", "a@a", "Mentor", "a"));
        allUsers.add(new User("s","s@s","Student", "s"));  //test student user
        allUsers.add(new User("s2","s@s","Student", "s2"));
        allUsers.add(new User("cekil","s@s","Student", "q"));
        allUsers.add(new User("cecil","s@s","Student", "c"));
        allUsers.get(1).getAttendance().setAttendacePerDays(new LocalDate(), true); //for attednace testing
        allUsers.get(2).getAttendance().setAttendacePerDays(new LocalDate(), false);
        allUsers.get(2).getAttendance().setAttendacePerDays(new LocalDate(), true);
        allSubPages.add(new TextPage("Test", "TestText"));
        allSubPages.add(new TextPage("Test2", "TestText2"));
        getAllSubPages().get(1).setPublished();
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

}