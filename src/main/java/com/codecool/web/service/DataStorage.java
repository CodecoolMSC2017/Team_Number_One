package com.codecool.web.service;

import com.codecool.web.dao.DatabaseUserDao;
import com.codecool.web.dao.UserDao;
import com.codecool.web.model.*;
import org.joda.time.LocalDate;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataStorage {
    private List<SubPage> allSubPages = new ArrayList<>();
    private List<Result> allResults = new ArrayList<>();
    private Connection connection;

    public DataStorage(Connection connection){
        this.connection=connection;
    }




    public void addSubPage(SubPage subPage) {

        int id = allSubPages.size();
        subPage.setId(id);
        allSubPages.add(subPage);
    }

    public List<SubPage> getAllSubPages() { return allSubPages; }



    public SubPage getSubPageById(int id){
        SubPage result = null;
        for (SubPage temp:allSubPages) {
            if(temp.getId() == id){
                result = temp;
                break;
            }
        }
        return result;
    }



    public List<Result> getAllResults() {
        return allResults;
    }

    public void addNewResult(Result result) {
        allResults.add(result);
    }
}