package com.codecool.web.model;

import java.util.ArrayList;
import java.util.List;

public class AssignmentPage extends SubPage {

    private List<Question> listOfQuestions;
    private int maxScore;

    public AssignmentPage(int id, String title, boolean published, List<Question> listOfQuestions, int maxScore) {
        super(id, title, published);
        this.listOfQuestions = listOfQuestions;
        this.maxScore = maxScore;
    }

    public AssignmentPage() {
        super(0, "", false);
        List<Question> listOfQuestions = new ArrayList<>();
        this.listOfQuestions = listOfQuestions;
        this.maxScore = 0;
    }

    public List<Question> getListOfQuestions() {
        return listOfQuestions;
    }

    public void addTask(Question q) {
        listOfQuestions.add(q);
    }

    public int getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }

}
