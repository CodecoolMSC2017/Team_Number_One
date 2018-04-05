package com.codecool.web.model;

import java.sql.Timestamp;

public class Result {

    private int assignmentPageId;
    private User user;
    private int score = 0;
    private Timestamp submissionDate;

    public Result(int assignmentPageId, User user, Timestamp submissionDate) {
        this.assignmentPageId = assignmentPageId;
        this.user = user;
        this.submissionDate = submissionDate;
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
}
