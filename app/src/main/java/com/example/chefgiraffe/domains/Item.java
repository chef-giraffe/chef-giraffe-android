package com.example.chefgiraffe.domains;

import java.math.BigDecimal;

public class Item {
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
    private String itemImageUrl;

    public Item(String id, String name, String description, BigDecimal price, String itemImageUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.itemImageUrl = itemImageUrl;
    }

    public Item(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getItemImageUrl() {
        return itemImageUrl;
    }
}
