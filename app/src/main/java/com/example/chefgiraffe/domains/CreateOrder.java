package com.example.chefgiraffe.domains;

import java.util.List;

public class CreateOrder {
    private String tableId;
    private List<String> orderItems;

    public CreateOrder(String tableId, List<String> orderItems) {
        this.tableId = tableId;
        this.orderItems = orderItems;
    }
}
