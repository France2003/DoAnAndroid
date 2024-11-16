package com.example.doanandroid;

public class Book {
    private String title;
    private String author;
    // Constructor
    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    // Getter cho title
    public String getTitle() {
        return title;
    }

    // Setter cho title
    public void setTitle(String title) {
        this.title = title;
    }

    // Getter cho author
    public String getAuthor() {
        return author;
    }

    // Setter cho author
    public void setAuthor(String author) {
        this.author = author;
    }
}
