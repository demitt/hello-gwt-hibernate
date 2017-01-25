package ua.demitt.homework.hellogwthibernate.server.dao.impl;

import org.junit.Before;
import org.junit.Test;
import ua.demitt.homework.hellogwthibernate.server.dao.UserDao;
import ua.demitt.homework.hellogwthibernate.shared.dto.UserDto;

import static org.junit.Assert.*;

public class UserDaoImplTest {

    private UserDao userDao;

    @Before
    public void setUp() throws Exception {
        this.userDao = new UserDaoImpl();
    }

    @Test
    public void testFindUser() throws Exception {
        //Given
        String loginExpected = "first";

        //When
        UserDto userDto = this.userDao.findUser(loginExpected);

        //Then
        assertNotNull("Existent user not found", userDto);
    }

    @Test
    public void testFindUserForNonExistentUser() throws Exception {
        //Given
        String loginExpected = "nonExistentLogin";

        //When
        UserDto userDto = this.userDao.findUser(loginExpected);

        //Then
        assertNull("Non-existent user found", userDto);
    }
}