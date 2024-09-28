package org.bookstore.demo.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bookstore.demo.entity.Order;
import org.bookstore.demo.entity.OrderState;
import org.bookstore.demo.service.InventoryService;
import org.bookstore.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private static final Log LOGGER = LogFactory.getLog(OrderController.class);

    @Autowired
    private OrderService orderService;

    @Autowired
    private InventoryService inventoryService;

    @PostMapping("/createOrder")
    public synchronized ResponseEntity<?> createOrder(@RequestBody Order order) {
        try {
            List<String> books = orderService.createOrder(order);
            if (!CollectionUtils.isEmpty(books)) {
                LOGGER.info("Books inventory of " + books + " is not available!");
                return ResponseEntity.ok("Books inventory of " + books + " is not available!");
            }

        } catch (Exception e) {
            LOGGER.error("Fail to create order for " + order.getOrderId(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fail to create order for " + order.getOrderId());
        }

        LOGGER.info("Create order for " + order.getOrderId() + " successfully!");
        return ResponseEntity.ok(order);
    }

    @PostMapping("/cancelOrder")
    public ResponseEntity<?> cancelOrder(@RequestParam Long orderId) {
        try {
            orderService.cancelOrder(orderId, OrderState.CANCELLED.getValue());
        } catch (Exception e) {
            LOGGER.error("Fail to cancel order for " + orderId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Fail to cancel order for " + orderId + StringUtils.SPACE + checkOrderState(orderId));
        }

        LOGGER.info("Order id " + orderId + "has been cancelled");
        return ResponseEntity.ok("Order id " + orderId + "has been cancelled: " + checkOrderState(orderId));
    }

    @PostMapping("/checkOrderState")
    public synchronized ResponseEntity<?> checkOrderState(@RequestParam Long orderId) {
        Order order = null;
        try {
            order = orderService.checkOrderState(orderId);
        } catch (Exception e) {
            LOGGER.error("Fail to check order state for " + orderId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Fail to check order state for " + orderId + "please try later");
        }

        LOGGER.info("Order state of " + orderId + " is " + order.getOrderState());
        return ResponseEntity.ok(order);
    }
}
