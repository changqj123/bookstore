package org.bookstore.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Long orderId;
    private Date orderTime;
    private Double orderPrice;
    private String orderState;
    private String user;
    private String orderItems;
}
