package com.lion.refactoring4;

import com.lion.domain.User;

import java.sql.SQLException;

public class UserDaoTest {
    /*팩토리사용 클래스
    UserDaoTest클래스의 본래의 성격은 UserDao를 test하는 목적의 클래스였다
    팩토리 패턴으 도입으로 UserDaoTest는 이제 오브젝트가 어떻게 만들어지는지 신경쓰지않고 팩토리로부터
    UserDao오브젝트를 받아다가 사용하면된다*/
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        // new DaoFactory().userDao().add(new User("15","박땡땡","32323"));
        UserDao userDao = new DaoFactory().userDao();
        userDao.deleteById("15");
        userDao.getCount();
    }

}
