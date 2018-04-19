package com.codecool.web.model;


import org.joda.time.Days;
import org.joda.time.LocalDate;

import java.text.SimpleDateFormat;
import java.util.*;

public class User {
    private String name;
    private String password;
    private String email = "";
    private String role = "";
    private int uniqueId;
    private UserAttendace attendance;
    private HashMap<Integer, HashMap<Question, String>> listOfCompletedAssignments = new HashMap<>();

    public User(int uniqueId,String name, String email, String role, String password) {
        this.name = name;
        this.email = email;
        this.role = (role == null) ? "Student" : role;
        this.password = password;
        this.uniqueId = uniqueId;
        attendance = new UserAttendace();
    }

    // for user checking at login, comes from the loginFilter
    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    @Deprecated
    private String generateId(){
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        SimpleDateFormat dateFormat = new SimpleDateFormat("00yyyyMMddHHmmssms");
        int counter = 0;

        while (counter <= 32){
            counter++;
            sb.append(Integer.toString(r.nextInt(9)));
        }
        sb.append(dateFormat.format(new Date()));

        return sb.toString();
    }

    public String getName() {
        return name;
    }


    public String getEmail() {
        return email;
    }


    public String getRole() {
        return role;
    }


    public int getUniqueId() {
        return uniqueId;
    }


    public int daysSinceRegistered(){
        return Days.daysBetween(attendance.getRegistrationDate(), new LocalDate()).getDays();
    }

    public UserAttendace getAttendance() {
        return attendance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) &&
                Objects.equals(password, user.password);
    }


    @Override
    public int hashCode() {
        return Objects.hash(name, password);
    }


    public HashMap<Integer, HashMap<Question, String>> getListOfCompletedAssignments() {
        return listOfCompletedAssignments;
    }

    public List<Integer> getAssignIds(){
        List<Integer> assignIds = new ArrayList<>(listOfCompletedAssignments.keySet());
        return assignIds;
    }


    /*
    @Deprecated
    public List<String> getAssignTitles(){
        List<String> assignTitles = new ArrayList<>();
        List<Integer> assignIds = this.getAssignIds();

        for (SubPage sp: DataStorage.getInstance().getAllSubPages()) {
            if(assignIds.contains(sp.getId())){
                assignTitles.add(sp.getTitle());
            }
        }
        return assignTitles;
    }*/

    public void addCompletedAssignment (int assignmentId, HashMap<Question, String> answer) {
        boolean isDuplicated = false;
        for (int tempId:listOfCompletedAssignments.keySet()) {
            if(tempId != assignmentId){
                continue;
            }
            isDuplicated = true;
        }
        if(!isDuplicated){
            listOfCompletedAssignments.put(assignmentId, answer);
        }
    }


    //for testing
    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", password='" + password + '\'' +
                ", uniqueId=" + uniqueId + '\'' +
                ", attendance=" + attendance + '\'' +
                " days_since_registered=" + daysSinceRegistered() +
                " }";
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(String role) {
        this.role = role;
    }
}