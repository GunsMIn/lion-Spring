package com.lion.refactoring4;

import com.lion.domain.User;

import java.sql.SQLException;

public class UserDaoTest {
    //팩토리사용 클래스
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        // new DaoFactory().userDao().add(new User("15","박땡땡","32323"));
        UserDao userDao = new DaoFactory().userDao();
        userDao.deleteById("15");
        userDao.getCount();
    }

}
