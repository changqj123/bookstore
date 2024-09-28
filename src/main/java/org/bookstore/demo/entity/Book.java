package org.bookstore.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private String categoryId;
    private Long bookId;
    private String bookName;
    private String author;
    private String publishHouse;
    private String description;
    private Double price;
}
