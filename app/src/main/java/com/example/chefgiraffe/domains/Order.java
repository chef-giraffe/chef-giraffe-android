package com.example.chefgiraffe.domains;

import java.util.List;

public class Order {
    private String id;
    private List<Item> itemDetails;

    public Order(String id, List<Item> itemDetails) {
        this.id = id;
        this.itemDetails = itemDetails;
    }

    public List<Item> getItemDetails() {
        return itemDetails;
    }
}
