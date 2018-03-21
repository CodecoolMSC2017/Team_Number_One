package com.codecool.web.model;

public class TextPage extends SubPage {

    private String textContent = "";

    public TextPage(String title, String textContent) {
        super(title);
        this.textContent = textContent;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }
}
