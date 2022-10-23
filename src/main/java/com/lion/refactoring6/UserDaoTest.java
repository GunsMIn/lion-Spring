package com.lion.refactoring6;

import com.lion.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = UserDaoFactory.class)
class UserDaoTest {

    //@Autowired 를 이용해서 Container에서 가져오게 하여 new 를 한번만 하도록 합니다
    @Autowired
    ApplicationContext context;

    UserDao userDao;
    User user1;
    User user2;
    User user3;

    @BeforeEach
    void setUp() {
        this.userDao = context.getBean("awsUserDao", UserDao.class);
        this.user1 = new User("30", "김영기", "1234");
        this.user2 = new User("31", "김건우", "1234");
        this.user3 = new User("32", "김건희", "1234");
    }


    //deleteALL까지 테스트 !
    @Test
    @DisplayName("add,get 메소드")
    void addAndGet() throws SQLException, ClassNotFoundException {
        User user1 = new User("1", "김수로", "1123");
        userDao.deleteAll();
        assertEquals(0,userDao.getCount());
        userDao.add(user1);
        assertEquals(1,userDao.getCount());

        User findUser = userDao.findById(user1.getId());
        assertEquals(user1.getName(), findUser.getName());
    }




    @Test
    @DisplayName("deleteAll메소드와 Add 테스트")
    public void count() throws SQLException, ClassNotFoundException {


        userDao.deleteAll();
        assertEquals(0,userDao.getCount());

        userDao.add(user1);
        assertEquals(1,userDao.getCount());

        userDao.add(user2);
        assertEquals(2,userDao.getCount());

        userDao.add(user3);
        assertEquals(3,userDao.getCount());

    }


    @Test
    void findById() {   //    () -> { }
        //2번째인수는 람다식으로 예외처리터지는 상황!
        assertThrows(EmptyResultDataAccessException.class, ()->{
            userDao.findById("100");
        });
    }




}