package com.example.doanandroid;

public class Genre {
    private String titleTL;
    private int quantityTL;
    public Genre(String titleTL, int quantityTL) {
        this.titleTL = titleTL;
        this.quantityTL = quantityTL;
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
