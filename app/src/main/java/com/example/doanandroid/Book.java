package com.example.doanandroid;

public class Book {
    private String title;
    private String author;
    private int quantity;
    private String description;
    public Book(String title, String author, int quantity, String description) {
        this.title = title;
        this.author = author;
        this.quantity = quantity;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }
    public int getQuanlity() {
        return quantity;
    }
    public String getDescription() {
        return description;
    }
    //
    public void setTitle(String title) {
        this.title = title;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
