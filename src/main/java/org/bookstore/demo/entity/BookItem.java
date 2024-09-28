package org.bookstore.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookItem {
    private Long bookId;
    private String bookName;
    private Long bookStock;
}
