package org.bookstore.demo.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bookstore.demo.entity.BookItem;
import org.bookstore.demo.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    private static final Log LOGGER = LogFactory.getLog(InventoryController.class);

    @Autowired
    private InventoryService inventoryService;

    @PostMapping("/increase")
    public ResponseEntity<?> increaseInventoryById(@RequestParam Long bookId, @RequestParam Integer num) {
        try {
            inventoryService.incStockById(bookId, num);
        } catch (Exception e) {
            LOGGER.error("Fail to increase inventory for " + bookId, e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Fail to increase inventory for " + bookId + ", please try again later");
        }

        LOGGER.info("Increase iventory for " + bookId + " Successfully!");
        return checkInventoryById(bookId);
    }

    @PostMapping("/decrease")
    public ResponseEntity<?> decreaseInventoryById(@RequestParam Long bookId, @RequestParam Integer num) {
        try {
            inventoryService.decStockById(bookId, num);
        } catch (Exception e) {
            LOGGER.error("Fail to decrease inventory for " + bookId, e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Fail to decrease inventory for " + bookId + ", please try again later");
        }

        LOGGER.info("Decrease inventory for " + bookId + " Successfully!");
        return checkInventoryById(bookId);
    }

    @PostMapping("/checkStockById")
    public ResponseEntity<?> checkInventoryById(@RequestParam Long bookId) {
        BookItem bookItem = null;
        try {
            bookItem = inventoryService.selectStockById(bookId);
        } catch (Exception e) {
            LOGGER.error("Error when check inventory for " + bookId, e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error when check inventory for " + bookId + ", please try later");

        }
        return ResponseEntity.ok(bookItem);
    }

    @PostMapping("/addBookItem")
    public ResponseEntity<?> addBookItem(@RequestBody BookItem bookItem) {
        try {
            inventoryService.addBookItem(bookItem);
        } catch (Exception e) {
            LOGGER.error("Fail to add book item for " + bookItem.getBookId(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Fail to add book item for " + bookItem + ", please try later");
        }

        LOGGER.info("Add book item for " + bookItem.getBookId() + " successfully");
        return ResponseEntity.ok(bookItem);
    }

    @PostMapping("/removeBookItem")
    public ResponseEntity<String> removeBookItem(@RequestParam Long bookId) {
        try {
            inventoryService.deleteBookItem(bookId);
        } catch (Exception e) {
            LOGGER.error("Fail to remove book item for " + bookId, e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Fail to remove book item for " + bookId + ", please try later");
        }

        LOGGER.info("Remove book item for " + bookId + " successfully");
        return ResponseEntity.ok("Remove book item for " + bookId + " successfully");
    }
}
