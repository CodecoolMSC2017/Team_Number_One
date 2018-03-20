package com.codecool.web.model;

import java.util.ArrayList;
import java.util.List;

public class AssignmentPage {

    private int assignId;
    private String assignTitle = "";
    private String assignQuestion = "";
    private List<String> assignAnswers = new ArrayList<>();
    private boolean isPublished = false;

    public AssignmentPage(String assignTitle, String assignQuestion, List<String> assignAnswers) {
        this.assignTitle = assignTitle;
        this.assignQuestion = assignQuestion;
        this.assignAnswers = assignAnswers;
    }

    public AssignmentPage() {}

    public int getAssignId() {
        return assignId;
    }

    public void setAssignId(int assignId) {
        this.assignId = assignId;
    }

    public String getAssignTitle() {
        return assignTitle;
    }

    public void setAssignTitle(String assignTitle) {
        this.assignTitle = assignTitle;
    }

    public String getAssignQuestion() {
        return assignQuestion;
    }

    public void setAssignQuestion(String assignQuestion) {
        this.assignQuestion = assignQuestion;
    }

    public List<String> getAssignAnswers() {
        return assignAnswers;
    }

    public void setAssignAnswers(List<String> assignAnswers) {
        this.assignAnswers = assignAnswers;
    }

    public boolean isPublished() {
        return isPublished;
    }

    public void setPublished() {
        isPublished = true;
    }

    public void setUnpublished() {
        isPublished = false;
    }
}
