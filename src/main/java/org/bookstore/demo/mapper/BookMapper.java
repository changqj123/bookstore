package org.bookstore.demo.mapper;

import org.bookstore.demo.entity.Book;

import java.util.List;

public interface BookMapper {
    void addBook(Book book);
    void deleteBookById(Long bookId);
    void updateBook(Book book);
    Book selectBookById(Long bookId);
    List<Book> selectBookByName(String bookName);
    List<Book> selectAllBooks();
}
