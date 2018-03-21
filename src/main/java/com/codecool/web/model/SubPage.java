package com.codecool.web.model;

public abstract class SubPage {
    private int id;
    private String title;
    private boolean published;

    public SubPage(String title) {
        this.title = title;
        this.published = false;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isPublished() {
        return published;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPublished() {
        this.published = !this.published;
    }
}