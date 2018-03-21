package com.codecool.web.model;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.*;

public class User {
    private String name;
    private String email;
    private String role;
    private String password;
    private String uniqueId;
    private List<Integer> listOfCompletedAssignmentIds = new ArrayList<>();

    public User(String name, String email, String role, String password) {
        Random r = new Random();
        this.name = name;
        this.email = email;
        this.role = role;
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

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }

    public String getUniqueId() {
        return uniqueId;
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

    public void addCompletedAssignmentId (int id) {
        listOfCompletedAssignmentIds.add(id);
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
