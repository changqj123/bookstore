package org.bookstore.demo.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bookstore.demo.entity.Book;
import org.bookstore.demo.service.BookService;
import org.bookstore.demo.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/books")
public class BookController {
    private static final Log LOGGER = LogFactory.getLog(BookController.class);

    @Autowired
    private BookService bookService;

    @Autowired
    private InventoryService inventoryService;

    @PostMapping("/add")
    public ResponseEntity<?> addBook(@RequestBody Book book) {
        try {
            bookService.addBook(book);
        } catch (Exception e) {
            LOGGER.error("Fail to add book: " + book.getBookId() + " to book store", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Fail to add book: " + book + " to book store");
        }

        LOGGER.info("Add book " + book.getBookId() + " successfully!");
        LOGGER.info("Add one bookItem for " + book.getBookId() + " successfully");
        return ResponseEntity.ok(book);
    }

    @PostMapping("/delete")
    public ResponseEntity<String> deleteBook(@RequestParam Long bookId) {
        try {
            bookService.deleteBookById(bookId);
        } catch (Exception e) {
            LOGGER.error("Fail to remove book for " + bookId + " from book store", e);
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Fail to remove book for " + bookId + " from book store");
        }

        LOGGER.info("Delete book for " + bookId + " from bookstore Successfully!");
        LOGGER.info("Delete book item for " + bookId + " from inventory Successfully!");
        return ResponseEntity.ok("Delete book: " + bookId + " successfully");
    }

    @PostMapping("/update")
    public ResponseEntity<Book> updateBookInfo(@RequestBody Book book) {
        try {
            bookService.updateBookInfo(book);
        } catch (Exception e) {
            LOGGER.error("Fail to update book for " + book.getBookId(), e);
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Fail to update book for " + book + " from book store");
        }

        LOGGER.info("Update book " + book.getBookId() + " Successfully!");
        return ResponseEntity.ok(book);
    }

    @PostMapping("/viewBookInfoById")
    public ResponseEntity<?> viewBookInfoById(@RequestParam Long bookId) {
        Book book = null;
        try {
            book = bookService.viewBookInfoById(bookId);
        } catch (Exception e) {
            LOGGER.error("Fail to get book info for " + bookId , e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Fail to get book info for " + bookId + " from book store");
        }

        if (Objects.isNull(book)) {
            LOGGER.info("Book not exists, please check if the bookId is correct!");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Book not exists, please check if the bookId is correct!");
        } else {
            LOGGER.info("Get book info for " + bookId + " successfully");
            return ResponseEntity.ok(book);
        }
    }

    @PostMapping("/viewBookInfoByName")
    public ResponseEntity<?> viewBookInfoByName(@RequestParam String bookName) {
        List<Book> book;
        try {
            book = bookService.viewBookInfoByName(bookName);
        } catch (Exception e) {
            LOGGER.error("Fail to get book info for " + bookName , e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Fail to get book info for " + bookName + " from book store");
        }

        if (CollectionUtils.isEmpty(book)) {
            LOGGER.info("Book not exists, please check if the bookName is correct!");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Book not exists, please check if the bookName is correct!");
        } else {
            LOGGER.info("Get book info for " + bookName + " successfully");
            return ResponseEntity.ok(book);
        }
    }
}
