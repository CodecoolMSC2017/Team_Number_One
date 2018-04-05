package com.codecool.web.model;

import com.codecool.web.service.DataStorage;

import java.sql.Timestamp;

public class Result {

    private int assignmentPageId;
    private User user;
    private int score = 0;
    private Timestamp submissionDate;
    private String assignmentPageTitle;

    public Result(int assignmentPageId, User user, Timestamp submissionDate) {
        this.assignmentPageId = assignmentPageId;
        this.user = user;
        this.submissionDate = submissionDate;
        this.assignmentPageTitle = DataStorage.getInstance().getSubPageById(assignmentPageId).getTitle();
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
}
