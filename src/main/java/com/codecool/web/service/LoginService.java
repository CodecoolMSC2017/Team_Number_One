package com.codecool.web.service;

import com.codecool.web.dao.UserDao;
import com.codecool.web.model.User;
import com.codecool.web.service.exceptions.NoUserRegisteredException;
import com.codecool.web.service.exceptions.UserNotRegisteredException;

import java.sql.SQLException;
import java.util.List;

public class LoginService {
    private User tempForLogin;
    private final UserDao registeredUser;

    public LoginService(String userName, String passw, UserDao registeredUsers) {
        this.tempForLogin = new User (userName, passw);
        this.registeredUser = registeredUsers;
    }

    public User fetchUser() throws SQLException, UserNotRegisteredException, NoUserRegisteredException {
        List<User> users = registeredUser.getAllUsers();
        if( users == null){
            throw new NoUserRegisteredException();
        }
        else {
            for (User usr : users) {
                if (usr.equals(tempForLogin)) {
                    return usr;
                }
            }
        }

        throw new UserNotRegisteredException();

    }
}
