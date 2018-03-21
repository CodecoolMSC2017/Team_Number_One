package com.codecool.web.model;

public class Answer {

    private String answer = "";
    private boolean isAnswerRight = false;

    public Answer(String answer, boolean isAnswerRight) {
        this.answer = answer;
        this.isAnswerRight = isAnswerRight;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean getIsAnswerRight() {
        return isAnswerRight;
    }

    public void setAnswerRight() {
        isAnswerRight = true;
    }

    public void setAnswerFalse() {
        isAnswerRight = false;
    }
}
