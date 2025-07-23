package com.studentmgt;

import com.studentmgt.dao.UserDao;
import com.studentmgt.model.Gender;
import com.studentmgt.model.Role;
import com.studentmgt.model.User;

public class Main {
    public static void main(String[] args) {
        UserDao userDao = new UserDao();

        User user = new User();
        user.setPersonId("1");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setGender(Gender.MALE);
        user.setPhoneNumber("1234567890");
        user.setUserName("johndoe");
        user.setPassword("password");
        user.setRole(Role.STUDENT);

        userDao.saveUser(user);

        System.out.println("User saved successfully!");
    }
}
