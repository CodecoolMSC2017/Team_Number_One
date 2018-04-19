package com.codecool.web.model;

import com.codecool.web.dao.SubPageDao;
import com.codecool.web.service.SubPageService;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Result {

    private int assignmentPageId;
    private User user;
    private int score = 2;
    private Timestamp submissionDate;
    private String assignmentPageTitle;
    private AssignmentPage ap;
    private SubPageService spService;

    public Result(int assignmentPageId, User user, Timestamp submissionDate, SubPageService spService) throws SQLException {
        this.assignmentPageId = assignmentPageId;
        this.user = user;
        this.submissionDate = submissionDate;
        this.assignmentPageTitle = spService.getSubPageById(assignmentPageId).getTitle();
        this.ap = (AssignmentPage) spService.getSubPageById(assignmentPageId);
    }

    public Result(int assignmentPageId, User user, Timestamp submissionDate, Connection connection) throws SQLException {
        this.spService = new SubPageService(new SubPageDao(connection));
        this.assignmentPageId = assignmentPageId;
        this.user = user;
        this.submissionDate = submissionDate;
        this.assignmentPageTitle = spService.getSubPageById(assignmentPageId).getTitle();
        this.ap = (AssignmentPage) spService.getSubPageById(assignmentPageId);
    }

    public int getAssignmentPageId() {
        return assignmentPageId;
    }

    public User getUser() {
        return user;
    }

    public int getScore() {
        return score;
    }

    public Timestamp getSubmissionDate() {
        return submissionDate;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getAssignmentPageTitle() {
        return assignmentPageTitle;
    }

    public AssignmentPage getAp() {
        return ap;
    }
}
