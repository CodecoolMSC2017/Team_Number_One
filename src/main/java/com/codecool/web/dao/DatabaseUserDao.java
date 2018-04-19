package com.codecool.web.dao;

import com.codecool.web.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseUserDao extends AbstractDao implements UserDao {

    public DatabaseUserDao(Connection connection){super(connection);}

    @Override
    public List<User> getAllUsers() throws SQLException {
        List<User> allUser = new ArrayList<>();
        String sql = "SELECT * FROM users;";
        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)){
            while(resultSet.next()){
                allUser.add(createUser(resultSet));
            }
        }

        return allUser;
    }

    @Override
    public User getUserById(int userId) throws SQLException {
        User user = null;
        String sql = "SELECT * FROM users " +
                "WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1,userId);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                user=createUser(resultSet);
            }
        }

        return user;
    }

    @Override
    public User getUserByName(String name) throws SQLException {
        User user = null;
        String sql = "SELECT * FROM users " +
                "WHERE username = ?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1,name);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                user=createUser(resultSet);
            }
        }

        return user;
    }

    @Override
    public void addUser(String email, String password, String userName, String userRole) throws SQLException {
        String sql = "INSERT INTO users (email,password,username,userrole) " +
                "VALUES(?,?,?,?);";
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            connection.setAutoCommit(false);
            statement.setString(1,email);
            statement.setString(2,password);
            statement.setString(3,userName);
            statement.setString(4,userRole);
            executeInsert(statement);
        }

    }

    @Override
    public void updateUser(int id, String name, String role) throws SQLException {
        String sql = "UPDATE users SET username=?,userrole=? " +
                "WHERE id=?";

        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1,name);
            statement.setString(2,role);
            statement.setInt(3,id);
            statement.executeUpdate();
        }

        //connection.commit();
    }


    private User createUser(ResultSet resultSet) throws SQLException{
        int id=resultSet.getInt("id");
        String name = resultSet.getString("username");
        String email = resultSet.getString("email");
        String role = resultSet.getString("userrole");
        String pwd = resultSet.getString("password");
        return new User(id,name,email,role,pwd);
    }
}
