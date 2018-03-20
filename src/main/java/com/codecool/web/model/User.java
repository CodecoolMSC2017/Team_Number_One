package com.codecool.web.model;

import java.util.Objects;
import java.util.Random;

public class User {
    private String name;
    private String email;
    private String role;
    private String password;
    private long uniqueId;

    public User(String name, String email, String role, String password) {
        Random r = new Random();
        this.name = name;
        this.email = email;
        this.role = role;
        this.password = password;
        uniqueId = r.nextLong(); // no checking for existing IDs at other users
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.email = "";
        this.role = "";
        uniqueId = 0;
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

    public long getUniqueId() {
        return uniqueId;
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
