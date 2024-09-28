package org.bookstore.demo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import org.bookstore.demo.entity.Book;
import org.bookstore.demo.entity.Order;
import org.bookstore.demo.entity.OrderItem;
import org.bookstore.demo.entity.OrderState;
import org.bookstore.demo.mapper.BookMapper;
import org.bookstore.demo.mapper.OrderMapper;
import org.bookstore.demo.service.InventoryService;
import org.bookstore.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private InventoryService inventoryService;

    @Override
    @Transactional
    public List<String> createOrder(Order order) {
        ArrayList<String> books = Lists.newArrayList();
        String orderItems = order.getOrderItems();
        List<OrderItem>  orderItemList = JSONObject.parseArray(orderItems, OrderItem.class);

        orderItemList.forEach(orderItem -> {
            Book book = JSONObject.parseObject(orderItem.getBook(), Book.class);
            Long bookId = book.getBookId();
            Long bookStock = inventoryService.selectStockById(bookId).getBookStock();
            if (bookStock <= 0) {
                books.add(book.getBookName());
            }
        });

        if (!CollectionUtils.isEmpty(books)) {
            return books;
        }

        orderMapper.addOrder(order);
        orderItemList.forEach(orderItem -> {
            Integer itemQuantity = orderItem.getItemQuantity();
            Book book = JSONObject.parseObject(orderItem.getBook(), Book.class);
            Long bookId = book.getBookId();
            inventoryService.decStockById(bookId, itemQuantity);
        });

        return Lists.newArrayList();
    }

    @Override
    @Transactional
    public void cancelOrder(Long orderId, String orderState) {
        Order order = checkOrderState(orderId);

        if (!order.getOrderState().equalsIgnoreCase(OrderState.CANCELLED.getValue())) {
            orderMapper.updateOrder(orderId, orderState);
            String orderItems = order.getOrderItems();
            List<OrderItem> orderItemList = JSONObject.parseArray(orderItems, OrderItem.class);
            orderItemList.forEach(orderItem -> {
                Integer itemQuantity = orderItem.getItemQuantity();
                Book book = JSONObject.parseObject(orderItem.getBook(), Book.class);
                Long bookId = book.getBookId();
                inventoryService.incStockById(bookId, itemQuantity);
            });
        }
    }

    @Override
    public Order checkOrderState(Long orderId) {
        return orderMapper.selectOrderState(orderId);
    }
}
