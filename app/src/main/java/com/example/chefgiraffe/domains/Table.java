package com.example.chefgiraffe.domains;

import java.util.ArrayList;
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

    public List<String> commaSeperatedOrderItems() {
        ArrayList<String> result = new ArrayList<>();
        for (Order order : orderDetails) {
            StringBuilder csItems = new StringBuilder();
            for (Item item : order.getItemDetails()) {
                csItems.append(item.getItemName());
                csItems.append(", ");
            }
            csItems.setLength(csItems.length() - 2);
            result.add(csItems.toString());
        }
        return result;
    }
}
