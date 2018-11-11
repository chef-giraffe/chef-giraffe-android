package com.example.chefgiraffe.domains;

import java.util.UUID;

public class Item {
    private String id;
    private String itemName;
    private String itemDescription;
    private double itemPrice;
    private String itemImageUrl;

    public Item(String id, String itemName, String itemDescription, double itemPrice, String itemImageUrl) {
        this.id = id;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemPrice = itemPrice;
        this.itemImageUrl = itemImageUrl;
    }

    public String getId() {
        return id;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public String getItemImageUrl() {
        return itemImageUrl;
    }
}
