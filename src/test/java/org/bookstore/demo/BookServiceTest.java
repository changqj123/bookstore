package org.bookstore.demo;

import org.bookstore.demo.entity.Book;
import org.bookstore.demo.mapper.BookMapper;
import org.bookstore.demo.mapper.InventoryMapper;
import org.bookstore.demo.service.impl.BookServiceImpl;
import org.bookstore.demo.service.impl.InventoryServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.List;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class BookServiceTest {

    @InjectMocks
    private BookServiceImpl bookService;

    @Mock
    private InventoryServiceImpl inventoryService;

    @Mock
    private BookMapper bookMapper;

    @Mock
    private InventoryMapper inventoryMapper;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddBook(){
        Book book = mock(Book.class);
        when(book.getBookId()).thenReturn(1L);
        when(book.getBookName()).thenReturn("java");

        bookService.addBook(book);
        verify(bookMapper, times(1)).addBook(book);
        verify(inventoryService, times(1)).addBookItem(any());
    }

    @Test
    public void testDeleteBookById(){
        Book book = mock(Book.class);
        when(book.getBookId()).thenReturn(1L);
        when(bookMapper.selectBookById(anyLong())).thenReturn(book);
        bookService.deleteBookById(1L);
        verify(bookMapper, times(1)).deleteBookById(anyLong());
        verify(bookMapper, times(1)).deleteBookById(anyLong());
        verify(inventoryService, times(1)).deleteBookItem(any());
    }

    @Test
    public void testUpdateBookInfo(){
        Book book = mock(Book.class);
        bookService.updateBookInfo(book);
        verify(bookMapper, times(1)).updateBook(book);
    }

    @Test
    public void testGetAllBooks(){
        List bookList = mock(List.class);
        when(bookMapper.selectAllBooks()).thenReturn(bookList);

        bookService.getAllBooks();
        verify(bookMapper, times(1)).selectAllBooks();
    }

    @Test
    public void testViewBookInfoById(){
        Book book = mock(Book.class);
        when(book.getBookId()).thenReturn(1L);

        bookService.viewBookInfoById(1L);
        verify(bookMapper, times(1)).selectBookById(anyLong());
    }

    @Test
    public void testViewBookInfoByName(){
        Book book = mock(Book.class);
        when(book.getBookName()).thenReturn("test");

        bookService.viewBookInfoByName("test");
        verify(bookMapper, times(1)).selectBookByName(anyString());
    }
}
