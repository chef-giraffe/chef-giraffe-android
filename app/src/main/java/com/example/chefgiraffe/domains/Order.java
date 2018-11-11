package com.example.chefgiraffe.domains;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private String id;
    private OrderStatus orderStatus;
    private List<Item> itemDetails;

    public Order() {
        itemDetails = new ArrayList<>();
    }

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

    public String commaSeparatedItems() {
        StringBuilder csItems = new StringBuilder();
        for (Item item : itemDetails) {
            csItems.append(item.getName());
            csItems.append(", ");
        }
        if (!itemDetails.isEmpty()) {
            csItems.setLength(csItems.length() - 2);
        }
        return csItems.toString();
    }

    public BigDecimal totalPrice() {
        BigDecimal total = new BigDecimal(0.0);
        for (Item item : itemDetails) {
            total = total.add(item.getPrice());
        }
        return total;
    }
}
