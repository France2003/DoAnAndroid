package com.example.doanandroid;

public class Genre {
    private String titleTL;
    private int quantityTL;
    private int id;
    public Genre(int id,String titleTL, int quantityTL) {
        this.titleTL = titleTL;
        this.id = id;
        this.quantityTL = quantityTL;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getTitleTL() {
        return titleTL;
    }
    public int getQuanlityTL() {
        return quantityTL;
    }
    //
    public void setTitle(String title) {
        this.titleTL = title;
    }
    public void setQuantity(int quantity) {
        this.quantityTL = quantity;
    }
}
