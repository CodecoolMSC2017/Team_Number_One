package com.codecool.web.model;

public abstract class SubPage {
    private int id;
    private String title;
    private boolean published;

    public SubPage(int id, String title, boolean published) {
        this.id = id;
        this.title = title;
        this.published = published;
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
        this.published = true;
    }

    public void setUnPublished(){
        this.published = false;
    }
}