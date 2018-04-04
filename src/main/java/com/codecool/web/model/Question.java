package com.codecool.web.model;

import java.util.ArrayList;
import java.util.List;

public class Question {

    private String question;
    private Answer answer;

    public Question(String question, Answer answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }
}
