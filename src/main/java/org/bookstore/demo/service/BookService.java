package org.bookstore.demo.service;

import org.bookstore.demo.entity.Book;

import java.util.List;

public interface BookService {
    void addBook(Book book);
    void deleteBookById(Long BookId);
    void updateBookInfo(Book book);
    List<Book> getAllBooks();
    Book viewBookInfoById(Long bookId);
    List<Book> viewBookInfoByName(String bookName);
}
