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
    //private List<SubPages> allPages = new ArrayList<SubPages>();
    private Map<Integer, SubPage> allSubPages = new HashMap<>();

    private DataStorage() {
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

    public void addSubPage(TextPage textPage) {

        int id = allSubPages.size();
        textPage.setId(id);
        allSubPages.put(id, textPage);
    }

    public Map<Integer, SubPage> getAllSubPages() { return allSubPages; }

}