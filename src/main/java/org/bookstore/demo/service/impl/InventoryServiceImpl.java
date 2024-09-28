package org.bookstore.demo.service.impl;

import org.bookstore.demo.entity.BookItem;
import org.bookstore.demo.mapper.InventoryMapper;
import org.bookstore.demo.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryServiceImpl implements InventoryService {
    @Autowired
    private InventoryMapper inventoryMapper;

    @Override
    public void incStockById(Long bookId, Integer num) {
        inventoryMapper.increaseInventory(bookId, num);
    }

    @Override
    public void decStockById(Long bookId, Integer num) {
        inventoryMapper.decreaseInventory(bookId, num);
    }

    @Override
    public BookItem selectStockById(Long bookId) {
        return inventoryMapper.selectInventoryById(bookId);
    }

    @Override
    public void addBookItem(BookItem bookItem) {
        inventoryMapper.addBookItem(bookItem);
    }

    @Override
    public void deleteBookItem(Long bookId) {
        inventoryMapper.deleteBookItem(bookId);
    }
}
