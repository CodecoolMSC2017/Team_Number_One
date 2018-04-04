package com.codecool.web.model;

import java.util.ArrayList;
import java.util.List;

public class AssignmentPage extends SubPage {

    private List<Question> listOfTasks = new ArrayList<>();

    public AssignmentPage(String title, List<Question> listOfTasks) {
        super(title);
        this.listOfTasks = listOfTasks;
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
}
