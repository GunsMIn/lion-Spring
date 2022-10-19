package com.lion.refactoring5;

import com.lion.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationExtensionsKt;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DaoFactory.class)
class UserDaoTest {

    @Autowired
    ApplicationContext context;

    @Test
    public void addAndGet() throws SQLException, ClassNotFoundException {
        UserDao userDao = context.getBean("UserDao", UserDao.class);
        String id = "23";
        userDao.add(new User(id,"Nunu","1112223qqqweee"));

        User user = userDao.findById(id);
        Assertions.assertEquals("Nunu", user.getName());
    }

}