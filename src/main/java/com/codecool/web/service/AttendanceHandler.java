package com.codecool.web.service;

import com.codecool.web.model.User;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


//return only the students list
public abstract class AttendanceHandler {
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

    public static User getGivenUserByName(String userName){
        if (userName.equals("")){
            return null;
        }
        List<User> tempStudents = getStudentUserList();
        for (User studUsr: tempStudents) {
            if (studUsr.getName().equals(userName)){  //no check for multiple name
                return studUsr;
            }
        }
        return null;
    }

    public static User getGivenUserByDate(String dateNoTime){
        if (dateNoTime.equals("")){
            return null;
        }
        LocalDate myDate = new LocalDate(dateNoTime);
        List<User> tempStudents = getStudentUserList();
        for (User studUsr: tempStudents) {
            Map<LocalDate, Boolean> tempMap = studUsr.getAttendance().getAttendacePerDays();
            for (LocalDate keys: tempMap.keySet()){
                if(keys.isEqual(myDate)){
                    return studUsr;
                }
            }
        }
        return null;
    }


    public static List<User> getMentorsUserList() {
        List<User> temp = new ArrayList<>();
        List<User> userList = DataStorage.getInstance().getUserList();
        for (User usr: userList) {
            if (usr.getRole().equals("Mentor")){
                temp.add(usr);
            }
        }
        return temp;

    }
}
