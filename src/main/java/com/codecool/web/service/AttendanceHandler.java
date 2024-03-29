package com.codecool.web.service;

import com.codecool.web.model.User;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


//return only the students list
public class AttendanceHandler {

    UserService uService;

    public AttendanceHandler(UserService uService){
        this.uService=uService;
    }

    public AttendanceHandler(){

    }


    public List<User> filterSearch(String studentName, String inputDate){
        if(studentName.equals("") && inputDate.equals("")){
            return getStudentUserList();
        }

        User nameMatch = getGivenUserByName(studentName);
        List<User> dateMatch = getGivenUserByDate(inputDate);
        List<User> result = new ArrayList<>();

        if (dateMatch != null){
            result.addAll(dateMatch);
        }
        if ((nameMatch != null) && !(result.contains(nameMatch))){
            nameMatch.getAttendance().setAttendacePerDays(new LocalDate(), false);
            result.add(nameMatch);
        }

        return result;
    }

    public void saveAttendance(String id, String date, String wasHere){
        LocalDate currentDate = makeLocalDate(date);
        List<User> studUsers = getStudentUserList();
        if(currentDate != null) {  //check if date is not empty
            for (User usr : studUsers) {
                if (Integer.toString(usr.getUniqueId()).equals(id)) {
                    usr.getAttendance().setAttendacePerDays(currentDate, wasHere != null);
                }
            }
        }
    }


    public List<User> getStudentUserList() {
        List<User> temp = new ArrayList<>();
        List<User> userList = uService.getUserList();
        for (User usr: userList) {
            if (usr.getRole().equals("Student")){
                temp.add(usr);
            }
        }
        return temp;
    }

    public User getGivenUserByName(String userName){
        if (userName.equals("")){   //refractor later
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

    public LocalDate makeLocalDate(String dateString){  //retrun null if empty string
        if (!(dateString.equals(""))) {
            return new LocalDate(dateString);
        }
        return null;

    }

    public List<User> getGivenUserByDate(String dateNoTime){
        if (dateNoTime.equals("")){   //refractor later
            return null;
        }
        LocalDate myDate = makeLocalDate(dateNoTime);
        List<User> tempStudents = getStudentUserList();
        List<User> result = new ArrayList<>();
        for (User studUsr: tempStudents) {
            Map<LocalDate, Boolean> tempMap = studUsr.getAttendance().getAttendacePerDays();
            for (LocalDate keys: tempMap.keySet()){
                if(keys.isEqual(myDate)){
                    result.add(studUsr);
                }
            }
        }
        return result;
    }


    public List<User> getMentorsUserList() {
        List<User> temp = new ArrayList<>();
        List<User> userList = uService.getUserList();
        for (User usr: userList) {
            if (usr.getRole().equals("Mentor")){
                temp.add(usr);
            }
        }
        return temp;

    }
}