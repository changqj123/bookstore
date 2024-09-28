package org.bookstore.demo.service;

import org.bookstore.demo.entity.BookItem;

public interface InventoryService {
   void incStockById(Long bookId, Integer num);
   void decStockById(Long bookId, Integer num);
   BookItem selectStockById(Long bookId);
   void addBookItem(BookItem bookItem);
   void deleteBookItem(Long bookId);
}
