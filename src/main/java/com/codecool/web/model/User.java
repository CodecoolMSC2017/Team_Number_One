package com.codecool.web.model;


import java.text.SimpleDateFormat;
import java.util.*;

public class User {
    private String name;
    private String email;
    private String role;
    private String password;
    private String uniqueId;

    private HashMap<Integer, HashMap<Question, String>> listOfCompletedAssignments = new HashMap<>();

    public User(String name, String email, String role, String password) {
        Random r = new Random();
        this.name = name;
        this.email = email;
        if(role==null) {
           this.role="Student";
        }else{
            this.role=role;
        }
        this.password = password;
        uniqueId = generateId();
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.email = "";
        this.role = "";
        uniqueId = "0";
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

    public void addCompletedAssignment (int assignmentId, HashMap<Question, String> answer) {
        listOfCompletedAssignments.put(assignmentId, answer);
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

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(String role) {
        this.role = role;
    }
}