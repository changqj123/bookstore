package org.bookstore.demo.mapper;

import org.apache.ibatis.annotations.Param;
import org.bookstore.demo.entity.BookItem;

public interface InventoryMapper {
    void increaseInventory(@Param("bookId") Long bookId, @Param("num") Integer num);
    void decreaseInventory(@Param("bookId")Long bookId, @Param("num")Integer num);
    BookItem selectInventoryById(Long bookId);

    void addBookItem(BookItem bookItem);
    void deleteBookItem(Long bookId);
}
