package com.codecool.web.service;

import com.codecool.web.model.AssignmentPage;
import com.codecool.web.model.SubPage;
import com.codecool.web.model.TextPage;
import com.codecool.web.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataStorage {
    private List<User> allUsers = new ArrayList<User>();
    private List<SubPage> allSubPages = new ArrayList<>();

    private DataStorage() {
        allUsers.add(new User("a", "a@a", "Mentor", "a"));
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

}