package com.codecool.web.service;

import com.codecool.web.model.AssignmentPage;
import com.codecool.web.model.TextPage;
import com.codecool.web.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataStorage {
    private List<User> allUsers = new ArrayList<User>();
    //private List<SubPages> allPages = new ArrayList<SubPages>();
    private Map<Integer, TextPage> allTextPages = new HashMap<>();
    private Map<Integer, AssignmentPage> allAssignmentPages = new HashMap<>();

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

    public void addTextPage(TextPage textPage) {

        int textId = allTextPages.size();
        textPage.setTextId(textId);
        allTextPages.put(textId, textPage);
    }

    public Map<Integer, TextPage> getAllTextPages() { return allTextPages; }

    public void addAssignmentPage(AssignmentPage assignPage) {

        int assignId = allAssignmentPages.size();
        assignPage.setAssignId(assignId);
        allAssignmentPages.put(assignId, assignPage);
    }

    public Map<Integer, AssignmentPage> getAllAssignmentPages() {
        return allAssignmentPages;
    }
}
