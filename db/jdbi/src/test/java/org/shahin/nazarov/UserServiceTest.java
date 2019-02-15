package org.shahin.nazarov;

import org.junit.Assert;
import org.junit.Test;
import org.shahin.nazarov.jdbi.dao.UserDao;
import org.shahin.nazarov.jdbi.model.User;
import org.shahin.nazarov.jdbi.service.UserService;

import java.util.*;

import static java.lang.System.out;

public class UserServiceTest {
    UserService userService = new UserService();

    @Test
    public void createTable(){
        userService.createTable();
        userService.list().forEach( u -> out.println(u));
    }


    @Test
    public void insert() {
        User user = new User();
        user.setId(100);
        user.setUsername("test");
        user.setPassword("password");
        userService.insert(user);
        Assert.assertEquals(user, userService.get(user.getId()));
    }


    @Test
    public void update() {
        User user = userService.get(100);
        user.setId(101);
        user.setUsername("test");
        user.setPassword("password");
        userService.update(user, 100);
        Assert.assertEquals(user, userService.get(101));
    }


    @Test
    public void remove() {
        userService.remove(101);
        Assert.assertNull(userService.get(101));
    }

    @Test
    public void get() {
        Optional<User> user = userService.getSafe(25);
        Assert.assertEquals(user.map(u -> u.getId()).orElse(0), new Integer(25));
    }

    @Test
    public void list() {
        Assert.assertEquals(userService.list().size(), 5);
    }

    @Test
    public void size(){
        Assert.assertEquals(userService.size(), 30);
    }
}
