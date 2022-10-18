package com.lion;

import com.lion.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;


class UserDaoTest {
    //수정
    @Test
    void addAndSelect() throws SQLException, ClassNotFoundException {
        UserDao userDao = new UserDao();
        User user = new User("8", "라이언", "1123");
        userDao.add(user);

        User selectedUser = userDao.findById("8");
        Assertions.assertEquals("라이언", selectedUser.getName());

    }

}