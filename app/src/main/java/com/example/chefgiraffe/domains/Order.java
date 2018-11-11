package com.example.chefgiraffe.domains;

import java.util.List;

public class Order {
    private String id;
    private OrderStatus orderStatus;
    private List<Item> itemDetails;

    public Order(String id, OrderStatus orderStatus, List<Item> itemDetails) {
        this.id = id;
        this.orderStatus = orderStatus;
        this.itemDetails = itemDetails;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public List<Item> getItemDetails() {
        return itemDetails;
    }

    public String commaSeperatedItems() {
        StringBuilder csItems = new StringBuilder();
        for (Item item : itemDetails) {
            csItems.append(item.getItemName());
            csItems.append(", ");
        }
        csItems.setLength(csItems.length() - 2);
        return csItems.toString();
    }

    public double totalPrice() {
        double total = 0.0;
        for (Item item : itemDetails) {
            total += item.getItemPrice();
        }
        return total;
    }
}
