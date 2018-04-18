package com.codecool.web.service;

import com.codecool.web.dao.SubPageDao;
import com.codecool.web.model.Question;
import com.codecool.web.model.SubPage;

import java.sql.SQLException;
import java.util.List;

public class SubPageService {

    private SubPageDao subPageDao;

    public SubPageService(SubPageDao subPageDao) {
        this.subPageDao = subPageDao;
    }

    public List<SubPage> getAllSubPages() throws SQLException {
        return subPageDao.findAllSubPages();
    }

    public SubPage getSubPageById(int id) throws SQLException {
        return subPageDao.findSubPageById(id);
    }

    public int saveTextPage(String title, String description) throws SQLException{
        return subPageDao.addTextPage(title, description);
    }

    public int saveAssignmentPage(String title, int maxscore, List<Question> questions) throws SQLException {
        return subPageDao.addAssignmentPage(title, maxscore, questions);
    }
}
