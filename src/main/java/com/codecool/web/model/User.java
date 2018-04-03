package com.codecool.web.model;


import java.text.SimpleDateFormat;
import java.util.*;

public class User {
    private String name;
    private String password;
    private String email = "";
    private String role = "";
    private String uniqueId = "0";
    private int grade = 100;
    private int attendance = 100;

    private HashMap<Integer, Integer> listOfCompletedAssignments = new HashMap<>();

    public User(String name, String email, String role, String password) {
        this.name = name;
        this.email = email;
        this.role = (role == null) ? "Student" : role;
        this.password = password;
        uniqueId = generateId();
    }

    // for user checking at login, comes from the loginFilter
    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    private String generateId(){
        Random r = new Random();
        StringBuilder sb = new StringBuilder("");
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


    public String getUniqueId() {
        return uniqueId;
    }

    public int getGrade() {
        return grade;
    }

    public int getAttendance() {
        return attendance;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public void setAttendance(int attendance) {
        this.attendance = attendance;
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


    public HashMap<Integer, Integer> getListOfCompletedAssignments() {
        return listOfCompletedAssignments;
    }


    public void addCompletedAssignment (int assignmentId, int result) {
        listOfCompletedAssignments.put(assignmentId, result);
    }


    //for testing
    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", password='" + password + '\'' +
                ", uniqueId=" + uniqueId +
                '}';
    }
}
