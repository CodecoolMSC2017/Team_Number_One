package com.codecool.web.model;

import java.util.ArrayList;
import java.util.List;

public class AssignmentPage extends SubPage {

    private String assignQuestion = "";
    private List<String> assignAnswers = new ArrayList<>();

    public AssignmentPage(String title, String assignQuestion, List<String> assignAnswers) {
        super(title);
        this.assignQuestion = assignQuestion;
        this.assignAnswers = assignAnswers;
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
}
