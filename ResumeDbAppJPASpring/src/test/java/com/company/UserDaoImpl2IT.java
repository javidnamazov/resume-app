package com.company;

import com.company.dao.impl.UserDaoImpl2;
import com.company.entity.User;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class) //Run spring context where objects stored
@SpringBootTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserDaoImpl2IT {
//    https://stackoverflow.com/questions/26881739/unable-to-get-spring-boot-to-automatically-create-database-schema

    @Autowired
    UserDaoImpl2 userDao;

    public void before(){

    }

    @Test
    public void testGetAll() {
        List<User> list=userDao.getAll(null, null, null);

        Assertions.assertEquals(2, list.size(), "user size must be 2");

//        System.out.println("list="+list);


    }
}
