package org.bookstore.demo.service.impl;

import org.bookstore.demo.entity.Book;
import org.bookstore.demo.entity.BookItem;
import org.bookstore.demo.mapper.BookMapper;
import org.bookstore.demo.service.BookService;
import org.bookstore.demo.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private InventoryService inventoryService;

    @Override
    @Transactional
    public void addBook(Book book) {
        bookMapper.addBook(book);
        BookItem bookItem = new BookItem(book.getBookId(), book.getBookName(), 1L);
        inventoryService.addBookItem(bookItem);
    }

    @Override
    @Transactional
    public void deleteBookById(Long bookId) {
        Book book = viewBookInfoById(bookId);
        if (Objects.nonNull(book)) {
            bookMapper.deleteBookById(bookId);
            inventoryService.deleteBookItem(bookId);
        }
    }

    @Override
    public void updateBookInfo(Book book) {
        bookMapper.updateBook(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookMapper.selectAllBooks();
    }

    @Override
    public Book viewBookInfoById(Long bookId) {
        return bookMapper.selectBookById(bookId);
    }

    @Override
    public List<Book> viewBookInfoByName(String bookName) {
        return bookMapper.selectBookByName(bookName);
    }
}
