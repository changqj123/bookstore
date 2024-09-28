package org.bookstore.demo;

import org.bookstore.demo.entity.User;
import org.bookstore.demo.mapper.UserMapper;
import org.bookstore.demo.service.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.Objects;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {
    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserMapper userMapper;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLogin(){
        when(userMapper.selectUser("test01", "11")).thenReturn(new User());
        User user = userService.login("test01", "11");
        assertTrue(Objects.nonNull(user));
    }

    @Test
    public void testViewPersonalInfoById(){
        when(userMapper.selectUserById(1L)).thenReturn(new User());
        User user = userService.viewPersonalInfoById(1L);
        assertTrue(Objects.nonNull(user));
    }

    @Test
    public void testViewPersonalInfoByName(){
        when(userMapper.selectUserByName("test01")).thenReturn(new User());
        User user = userService.viewPersonalInfoByName("test01");
        assertNotNull(user);
    }

    @Test
    public void testRegister(){
        User newUser = new User();
        newUser.setUserId(11L);
        newUser.setUserName("test101");
        newUser.setPassword("101");
        newUser.setPhoneNum("12343333333");
        newUser.setEmail("123@qq.com");

        User user = mock(User.class);
        userService.register(user);
        verify(userMapper, times(1)).addUser(user);
    }
}
