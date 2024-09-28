package org.bookstore.demo.entity;

import lombok.Data;

import java.util.List;

@Data
public class OrderFrontend {
    private Order order;
    private User user;
    private List<OrderItem> orderItems;
}
