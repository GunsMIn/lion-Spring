package com.lion.refactoring4;

public class DaoFactory {
    //팩토리 클래스
    //여기서 주입
    public UserDao userDao() {
        //어떤 디비를 사용 할 것인지 팩토리 클래스에서 정함
        ConnectionMaker connectionMaker = new DConnectionMaker();
        UserDao userDao = new UserDao(connectionMaker);
        return userDao;
        }
}
