package com.example.casper.Experiment2024.data;

import java.io.Serializable;

public class Item implements Serializable {
    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public int getResourceId() {
        return resourceId;
    }

    private String title;

    public void setTitle(String itemName) {
        this.title=itemName;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    private double price;
    private final int resourceId;

    public Item(String itemTitle, double itemPrice, int itemPictureResourceId) {
        this.title=itemTitle;
        this.price=itemPrice;
        this.resourceId=itemPictureResourceId;
    }

}
