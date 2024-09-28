package org.bookstore.demo;

import org.bookstore.demo.entity.BookItem;
import org.bookstore.demo.mapper.InventoryMapper;
import org.bookstore.demo.service.impl.InventoryServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class InventoryServiceTest {
    @InjectMocks
    private InventoryServiceImpl inventoryService;

    @Mock
    private InventoryMapper inventoryMapper;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddBookItem() {
        BookItem bookItem = mock(BookItem.class);
        inventoryService.addBookItem(bookItem);

        verify(inventoryMapper, times(1)).addBookItem(bookItem);
    }

    @Test
    public void testIncStockById() {
        inventoryService.incStockById(1L, 30);
        verify(inventoryMapper, times(1)).increaseInventory(anyLong(), anyInt());
    }

    @Test
    public void testDecStockById() {
        inventoryService.decStockById(1L, 30);
        verify(inventoryMapper, times(1)).decreaseInventory(anyLong(), anyInt());
    }

    @Test
    public void testSelectStockById() {
        inventoryService.selectStockById(1L);
        verify(inventoryMapper, times(1)).selectInventoryById(anyLong());
    }

    @Test
    public void testDeleteBookItem() {
        inventoryService.deleteBookItem(1L);
        verify(inventoryMapper, times(1)).deleteBookItem(anyLong());
    }
}
