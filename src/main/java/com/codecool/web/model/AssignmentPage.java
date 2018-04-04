package com.codecool.web.model;

import java.util.ArrayList;
import java.util.List;

public class AssignmentPage extends SubPage {

    private List<Question> listOfTasks = new ArrayList<>();
    private int maxScore;

    public AssignmentPage(String title, List<Question> listOfTasks, int maxScore) {
        super(title);
        this.listOfTasks = listOfTasks;
        this.maxScore = maxScore;
    }

    public List<Question> getListOfTasks() {
        return listOfTasks;
    }

    public void setListOfTasks(List<Question> listOfTasks) {
        this.listOfTasks = listOfTasks;
    }

    public void addTask(Question q){
        listOfTasks.add(q);
    }

    public int getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }
}
