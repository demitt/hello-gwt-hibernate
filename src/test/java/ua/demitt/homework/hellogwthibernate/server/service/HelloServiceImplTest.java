package ua.demitt.homework.hellogwthibernate.server.service;

import org.junit.Before;
import org.junit.Test;
import ua.demitt.homework.hellogwthibernate.client.HelloService;
import ua.demitt.homework.hellogwthibernate.server.dao.UserDao;
import ua.demitt.homework.hellogwthibernate.shared.dto.UserDto;
import ua.demitt.homework.hellogwthibernate.shared.response.Response;
import ua.demitt.homework.hellogwthibernate.shared.response.ResponseCode;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HelloServiceImplTest {

    private HelloService helloService;

    @Before
    public void setUp() throws Exception {
        this.helloService = new HelloServiceImpl();
    }

    @Test
    public void testLoginWithNonExistentLogin() throws Exception {
        //Given
        String nonExistentLogin = "nonExistentLogin";

        UserDao userDao = mock(UserDao.class);
        when(userDao.findUser(nonExistentLogin)).thenReturn(null);
        HelloServiceImpl.userDao = userDao;

        //When
        Response response = this.helloService.login(nonExistentLogin, "somePassword");

        //Then
        assertEquals("Non-existent user found", ResponseCode.USER_NOT_FOUND, response.getCode());
    }

    @Test
    public void testLoginWithInvalidPassword() throws Exception {
        //Given
        String existentLogin = "first";

        UserDao userDao = mock(UserDao.class);
        when(userDao.findUser(existentLogin)).thenReturn(null);
        HelloServiceImpl.userDao = userDao;

        //When
        Response response = this.helloService.login(existentLogin, "somePassword");

        //Then
        assertEquals("Existent user with invalid password found", ResponseCode.USER_NOT_FOUND, response.getCode());
    }

    @Test
    public void testLogin() throws Exception {
        //Given
        Integer id = 1;
        String existentLogin = "first";
        String password = "secret";
        String passwordHash = "$2a$10$C4wCQNiA0ac4uFvpa8UahujzAh16Eeg45zLcIwOP36R70TnNG6FUu";
        UserDto userDto = new UserDto();
        userDto.setId(id);
        userDto.setPassword(passwordHash);

        UserDao userDao = mock(UserDao.class);
        when(userDao.findUser(existentLogin)).thenReturn(userDto);
        HelloServiceImpl.userDao = userDao;

        //When
        Response response = this.helloService.login(existentLogin, password);

        //Then
        assertEquals("Existent user with valid password not found", ResponseCode.OK, response.getCode());
    }
}