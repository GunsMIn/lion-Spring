package com.lion.lastRefactoring;

import com.lion.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = UserDaoFactory.class)
class UserDaoTest {

    @Autowired
    ApplicationContext context; //

    //deleteALL까지 테스트 !
    @Test
    @DisplayName("add,get 메소드")
    void addAndGet() throws SQLException, ClassNotFoundException {
        UserDao userDao = context.getBean("awsUserDao", UserDao.class);
        User user1 = new User("1", "김수로", "1123");

        userDao.add(user1);
        assertEquals(1,userDao.getCount());

        User findUser = userDao.findById(user1.getId());
        assertEquals(user1.getName(), findUser.getName());
    }



    @Test
    @DisplayName("deleteAll메소드와 Add 테스트")
    public void count() throws SQLException, ClassNotFoundException {
        UserDao userDao = context.getBean("awsUserDao", UserDao.class);
        User user1 = new User("30", "김영기", "1234");
        User user2 = new User("31", "김건우", "1234");
        User user3 = new User("32", "김건희", "1234");

        userDao.deleteAll();
        assertEquals(0,userDao.getCount());

        userDao.add(user1);
        assertEquals(1,userDao.getCount());

        userDao.add(user2);
        assertEquals(2,userDao.getCount());

        userDao.add(user3);
        assertEquals(3,userDao.getCount());

    }
}