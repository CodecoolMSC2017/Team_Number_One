package com.codecool.web.model;

public class TextPage extends SubPage {

    private String textContent = "";

    public TextPage(int id, String title, boolean published, String textContent) {
        super(id, title, published);
        this.textContent = textContent;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }
}
