package com.codecool.web.model;

import com.codecool.web.service.DataStorage;

import java.sql.Timestamp;

public class Result {

    private int assignmentPageId;
    private User user;
    private int score = 2;
    private Timestamp submissionDate;
    private String assignmentPageTitle;
    private AssignmentPage ap;

    public Result(int assignmentPageId, User user, Timestamp submissionDate) {
        this.assignmentPageId = assignmentPageId;
        this.user = user;
        this.submissionDate = submissionDate;
        this.assignmentPageTitle = DataStorage.getInstance().getSubPageById(assignmentPageId).getTitle();
        this.ap = (AssignmentPage)DataStorage.getInstance().getSubPageById(assignmentPageId);
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
