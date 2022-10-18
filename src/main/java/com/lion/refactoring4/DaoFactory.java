package com.lion.refactoring4;

public class DaoFactory {
    //팩토리 클래스
    //팩토리 클래스의 주 목적은 객체의 생성방법을 결정하고 그렇게 만들어진 오브젝트를 돌려주는 것이다.
    //다른 말로 말하자면 오브젝트를 생성하는 쪽과 사용하는 쪽의 역할과 책임을 깔끔하게 분리하려는 목적으로 사용한다.
    //그럼 DaoFactory는 객체를 생성하는(정하는) 클래스. UserDaoTest는 객체를 사용하는 클래스이다.
    public UserDao userDao() {
        //어떤 디비를 사용 할 것인지 팩토리 클래스에서 정함
        ConnectionMaker connectionMaker = new DConnectionMaker();
        UserDao userDao = new UserDao(connectionMaker);
        return userDao;
        }
}
