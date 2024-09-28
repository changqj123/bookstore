package org.bookstore.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    private Long itemId;
    private String book;
    private Integer itemQuantity;
    private Double itemPrice;
}
