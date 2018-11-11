package com.example.chefgiraffe.domains;

import java.util.List;

public class Table {
    private String id;
    private String friendlyName;
    private List<Order> orderDetails;

    public Table(String id, String friendlyName, List<Order> orderDetails) {
        this.id = id;
        this.friendlyName = friendlyName;
        this.orderDetails = orderDetails;
    }

    public String getId() {
        return id;
    }

    public String getFriendlyName() {
        return friendlyName;
    }

    public List<Order> getOrderDetails() {
        return orderDetails;
    }
}
