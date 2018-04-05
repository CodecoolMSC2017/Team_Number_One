package com.codecool.web.model;

import java.sql.Timestamp;

public class Result {

    private int assignmentPageId;
    private User user;
    private int score = 2;
    private Timestamp submissionDate;
    private AssignmentPage ap;

    public Result(int assignmentPageId, User user, Timestamp submissionDate, AssignmentPage ap) {
        this.assignmentPageId = assignmentPageId;
        this.user = user;
        this.submissionDate = submissionDate;
        this.ap=ap;
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

    public AssignmentPage getAp() {
        return ap;
    }
}
