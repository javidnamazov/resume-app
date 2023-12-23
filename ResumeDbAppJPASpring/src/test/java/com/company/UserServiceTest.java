package com.company;

import com.company.dao.impl.UserDaoImpl2;
import com.company.dao.impl.UserRepositoryCustom;
import com.company.entity.Country;
import com.company.entity.User;
import com.company.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.ArrayList;
import java.util.List;

public class UserServiceTest {

    @Mock
    UserDaoImpl2 userDao;

    @InjectMocks
    UserServiceImpl userService;

    @BeforeAll
    public static void setUp() {
        System.out.println("BeforeAll called");
    }

    @BeforeEach
    public void before() {
        System.out.println("BeforeEach called");

        MockitoAnnotations.initMocks(this);

        List<User> list = new ArrayList<>();

        User u = new User();
        u.setName("test");
        u.setSurname("test");
        u.setEmail("test@test.com");
        u.setNationality(new Country(1));

        list.add(u);

        Mockito.when(userDao.getAll(null, null, null)).thenReturn(list);

        Mockito.when(userDao.getAll("test", "test", 1)).thenReturn(list);

        Mockito.when(userDao.findByEmailAndPassword(null, null)).thenReturn(null);

        Mockito.when(userDao.getI()).thenReturn(1);
        Mockito.when(userDao.test()).thenReturn("alma");

    }

    @Test
    public void testGivenNullThenGetAll() {
        List<User> list = userService.getAll(null, null, null);

        Assertions.assertEquals(1, list.size(), "user size must be 1");

        Mockito.verify(userDao, Mockito.atLeastOnce())
                .getAll(null, null, null);
    }

    @Test
    public void testGivenAllParamsThenGetAllByFilter() {
        List<User> list = userService.getAll("test", "test", 1);

        Assertions.assertTrue(list.size() > 0, "user size must be greater than 0");

        User user = list.get(0);

        Assertions.assertEquals("test", user.getName(), "name wrong");
        Assertions.assertEquals("test", user.getSurname(), "surname wrong");
        Assertions.assertEquals(1, user.getNationality().getId(), "nat id wrong");

        Mockito.verify(userDao, Mockito.atLeastOnce())
                .getAll("test", "test", 1);

    }

    @Test
    public void testGivenNullFindByEmailAndPassword() {
        User user = userService.findByEmailAndPassword(null, null);

        Assertions.assertNull(user, "user size must be null");

        Mockito.verify(userDao, Mockito.atLeastOnce())
                .findByEmailAndPassword(null, null);
    }

}
