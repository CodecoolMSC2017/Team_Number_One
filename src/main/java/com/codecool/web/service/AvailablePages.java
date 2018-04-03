package com.codecool.web.service;

import com.codecool.web.model.SubPage;
import com.codecool.web.model.User;

import java.util.ArrayList;
import java.util.List;

public class AvailablePages {
    DataStorage ds = DataStorage.getInstance();

    public List<SubPage> selectPages(User user) {
        List<SubPage> available = new ArrayList<>();

        if (user.getRole().equals("Mentor")) {
            available = ds.getAllSubPages();
        }else if (user.getRole().equals("Student")) {
            for (SubPage page:ds.getAllSubPages()) {
                if (page.isPublished()) {
                    available.add(page);
                }
            }
        }
        return available;
    }
}
