package com.example.doanandroid;

public class Author {
    private String titleAuthor;
    private String yearAuthor;
    private int id;

    public Author(String titleAuthor, String yearAuthor, int id) {
        this.titleAuthor = titleAuthor;
        this.yearAuthor = yearAuthor;
        this.id = id;
    }

    public String getTitleAuthor() {
        return titleAuthor;
    }

    public String getYearAuthor() {
        return yearAuthor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

