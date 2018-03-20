package com.codecool.web.service;

import com.codecool.web.model.User;

import java.util.ArrayList;
import java.util.List;

public class DataStorage {
    private List<User> allUsers = new ArrayList<User>();
    //private List<SubPages> allPages = new ArrayList<SubPages>();

    private DataStorage() {
    }

    private static DataStorage ourInstance = new DataStorage();

    public static DataStorage getInstance() {
        return ourInstance;
    }

    public void addList(User user){
        allUsers.add(user);
    }
}
