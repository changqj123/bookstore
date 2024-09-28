package org.bookstore.demo.service;

import org.bookstore.demo.entity.Order;
import java.util.List;

public interface OrderService {
    List<String> createOrder(Order order);
    void cancelOrder(Long orderId, String orderState);
    Order checkOrderState(Long orderId);
}
