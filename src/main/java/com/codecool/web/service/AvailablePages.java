package com.codecool.web.service;

import com.codecool.web.dao.SubPageDao;
import com.codecool.web.model.SubPage;
import com.codecool.web.model.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AvailablePages {

    public List<SubPage> selectPages(Connection connection, User user) throws SQLException {
        SubPageDao spd = new SubPageDao(connection)
        List<SubPage> available = new ArrayList<>();

        if (user.getRole().equals("Mentor")) {
            available = spd.getAllSubPages();
        }else if (user.getRole().equals("Student")) {
            for (SubPage page:spd.getAllSubPages()) {
                if (page.isPublished()) {
                    available.add(page);
                }
            }
        }
        return available;
    }
}
