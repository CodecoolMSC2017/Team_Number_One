package com.codecool.web.service;


import com.codecool.web.model.User;

import java.util.ArrayList;
import java.util.List;

public class AttendanceHandler {

    public static List<User> getStudentUserList() {
        List<User> temp = new ArrayList<>();
        List<User> userList = DataStorage.getInstance().getUserList();
        for (User usr: userList) {
            if (usr.getRole().equals("Student")){
                temp.add(usr);
            }
        }
        return temp;

    }
}
