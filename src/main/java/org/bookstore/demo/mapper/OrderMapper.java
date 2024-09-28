package org.bookstore.demo.mapper;

import org.apache.ibatis.annotations.Param;
import org.bookstore.demo.entity.Order;

public interface OrderMapper {
    void addOrder(Order order);
    void updateOrder(@Param("orderId") Long orderId, @Param("orderState") String orderState);
    Order selectOrderState(Long orderId);
}
