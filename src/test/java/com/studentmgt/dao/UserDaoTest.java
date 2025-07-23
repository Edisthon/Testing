package com.studentmgt.dao;

import com.studentmgt.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserDaoTest {

    @Test
    public void testSaveUser() {
        UserDao userDao = new UserDao();
        User user = new User();
        user.setPersonId("testuser");
        user.setUserName("testuser");
        user.setPassword("password");
        userDao.saveUser(user);
        UserDao userDao = new UserDao();
        User user = new User();
        user.setPersonId("testuser");
        user.setUserName("testuser");
        user.setPassword("password");
        userDao.saveUser(user);
        assertTrue(new UserDao().authenticateUser("testuser", "password"));
    }
}
