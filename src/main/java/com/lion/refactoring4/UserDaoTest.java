package com.lion.refactoring4;

import com.lion.domain.User;

import java.sql.SQLException;

public class UserDaoTest {
    //팩토리사용 클래스
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserDao dao = new DaoFactory().userDao();
    }

}
