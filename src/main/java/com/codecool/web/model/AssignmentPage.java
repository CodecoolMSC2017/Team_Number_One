package com.codecool.web.model;

import java.util.ArrayList;
import java.util.List;

public class AssignmentPage extends SubPage {

    private List<Question> listOfQuestions = new ArrayList<>();
    private int maxScore;

    public AssignmentPage(String title, List<Question> listOfQuestions, int maxScore) {
        super(title);
        this.listOfQuestions = listOfQuestions;
        this.maxScore = maxScore;
    }

    public List<Question> getListOfQuestions() {
        return listOfQuestions;
    }

    public void setListOfQuestions(List<Question> listOfQuestions) {
        this.listOfQuestions = listOfQuestions;
    }

    public void addTask(Question q){
        listOfQuestions.add(q);
    }

    public int getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }
}
