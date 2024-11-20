package com.example.doanandroid;

public class Author {
    private String titleAuthor;
    private String yearAuthor;
    private String informationAuthor;

    public Author(String titleAuthor, String yearAuthor, String informationAuthor) {
        this.titleAuthor = titleAuthor;
        this.yearAuthor = yearAuthor;
        this.informationAuthor = informationAuthor;
    }

    public String getTitleAuthor() {
        return titleAuthor;
    }

    public String getYearAuthor() {
        return yearAuthor;
    }

    public String getInformationAuthor() {
        return informationAuthor;
    }
}

