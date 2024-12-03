package com.example.casper.Experiment2024;

public class Book {
    private int coverResourceId;
    private String title;

    public Book(String title, int coverResourceId) {
        this.title = title;
        this.coverResourceId = coverResourceId;
    }

    public int getCoverResourceId() {
        return coverResourceId;
    }

    public String getTitle() {
        return title;
    }
}