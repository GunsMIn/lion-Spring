package com.lion.refactoring4;

import com.lion.domain.User;

import java.sql.SQLException;

public class UserDaoTest {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ConnectionMaker connectionMaker = new DConnectionMaker();

        UserDao userDao = new UserDao(connectionMaker);
        User user = new User("14", "팩토리", "123423");
        userDao.add(user);

    }

}
