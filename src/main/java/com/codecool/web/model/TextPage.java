package com.codecool.web.model;

public class TextPage {

    private int textId;
    private String textTitle = "";
    private String textContent = "";
    private boolean isPublished = false;

    public TextPage(String textTitle, String textContent) {
        this.textTitle = textTitle;
        this.textContent = textContent;
    }

    public int getTextId() {
        return textId;
    }

    public void setTextId(int textId) {
        this.textId = textId;
    }

    public TextPage() {}

    public String getTextTitle() {
        return textTitle;
    }

    public void setTextTitle(String textTitle) {
        this.textTitle = textTitle;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public boolean isPublished() {
        return isPublished;
    }

    public void setPublished() {
        isPublished = true;
    }

    public void setUnpublished() {
        isPublished = false;
    }
}
