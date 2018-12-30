package com.example.romisaa.fashionboutique.data.model;

public class ProductItemModel {
    int num;
    String image;
    String description;
    String price;

    public ProductItemModel() {
    }

    public ProductItemModel(int num, String image, String description, String price) {
        this.num = num;
        this.image = image;
        this.description = description;
        this.price = price;
    }

    public int getNum() {
        return num;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }
}
