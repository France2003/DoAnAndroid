package com.example.doanandroid;

public class Book {
    private int id;
    private String title;
    private String author;

    public Book(int id,String title, String author) {
        this.title = title;
        this.author = author;
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }


    //
    public void setTitle(String title) {
        this.title = title;
    }
    public void setAuthor(String author) {
        this.author = author;
    }


}
