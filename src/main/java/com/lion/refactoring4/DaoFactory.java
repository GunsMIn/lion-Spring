package com.lion.refactoring4;

public class DaoFactory {
    //팩토리 클래스
    public UserDao userDao() {
        ConnectionMaker connectionMaker = new DConnectionMaker();
        UserDao userDao = new UserDao(connectionMaker);
        return userDao;
        }
}
