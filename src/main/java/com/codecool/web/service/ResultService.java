package com.codecool.web.service;

import com.codecool.web.dao.ResultDao;
import com.codecool.web.model.Result;

import java.sql.SQLException;
import java.util.List;

public class ResultService {

    private ResultDao resultDao;

    public ResultService(ResultDao resultDao) {
        this.resultDao = resultDao;
    }

    public List<Result> getAllResults() throws SQLException {
        return resultDao.findAllResults();
    }

    public List<Result> getResultsByUserId(int userId) throws SQLException {
        return resultDao.findResultsByUserId(userId);
    }

    public List<Result> getResultsByPageId(int pageId) throws SQLException {
        return resultDao.findResultsByPageId(pageId);
    }

    public void saveResult(Result result) throws SQLException {
        resultDao.addResult(result);
    }
}