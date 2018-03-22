package com.codecool.web.model;

import java.util.ArrayList;
import java.util.List;

public class Question {

    private String question = "";
    private List<Answer> listOfAnswers = new ArrayList<>();

    public Question(String question, List<Answer> listOfAnswers) {
        this.question = question;
        this.listOfAnswers = listOfAnswers;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<Answer> getListOfAnswers() {
        return listOfAnswers;
    }

    public void setListOfAnswers(List<Answer> listOfAnswers) {
        this.listOfAnswers = listOfAnswers;
    }

    public List<Answer> getRightAnswers() {

        List<Answer> rightAnswers = new ArrayList<>();
        for (Answer tempAns : this.listOfAnswers) {
            if (tempAns.getIsAnswerRight()) rightAnswers.add(tempAns);
        }

        return rightAnswers;
    }

    public List<String> getRightStringAnswers() {

        List<String> rightAnswers = new ArrayList<>();
        for (Answer tempAns : this.listOfAnswers) {
            if (tempAns.getIsAnswerRight()) rightAnswers.add(tempAns.getAnswer());
        }

        return rightAnswers;
    }
}
